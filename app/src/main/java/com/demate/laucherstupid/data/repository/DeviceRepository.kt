package com.demate.laucherstupid.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceRepository @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("device_prefs", Context.MODE_PRIVATE)
    }

    enum class DeviceIdType { INSTALLATION, ANDROID_ID, SERIAL }

    fun getDeviceId(type: DeviceIdType = DeviceIdType.ANDROID_ID): String {
        return when (type) {
            DeviceIdType.ANDROID_ID -> getAndroidId() ?: getInstallationId()
            DeviceIdType.SERIAL -> getDeviceSerial() ?: getAndroidId() ?: getInstallationId()
            DeviceIdType.INSTALLATION -> getInstallationId()
        }
    }

    private fun getInstallationId(): String {
        val existing = prefs.getString(KEY_INSTALLATION_ID, null)
        if (!existing.isNullOrBlank()) return existing
        val newId = UUID.randomUUID().toString()
        prefs.edit { putString(KEY_INSTALLATION_ID, newId) }
        return newId
    }

    @SuppressLint("HardwareIds")
    private fun getAndroidId(): String? {
        return try {
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                ?.takeIf { it.isNotBlank() }
        } catch (_: Exception) {
            null
        }
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    private fun getDeviceSerial(): String? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                null
            } else {
                val hasPermission = ContextCompat.checkSelfPermission(
                    context, Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
                if (!hasPermission) return null
                val serial = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        Build.getSerial()
                    } catch (_: SecurityException) {
                        null
                    }
                } else {
                    @Suppress("DEPRECATION")
                    Build.SERIAL
                }
                serial?.takeIf { it.isNotBlank() && it != UNKNOWN }
            }
        } catch (_: Exception) {
            null
        }
    }

    private companion object {
        const val KEY_INSTALLATION_ID = "installation_id"
        const val UNKNOWN = "unknown"
    }
}
