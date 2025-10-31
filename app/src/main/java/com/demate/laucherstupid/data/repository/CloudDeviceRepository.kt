package com.demate.laucherstupid.data.repository


import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudDeviceRepository @Inject constructor(
    firestore: FirebaseFirestore,
    private val authRepository: AuthRepository
) {
    private val collection = firestore.collection("devices")

    suspend fun isDeviceRegistered(serial: String?, imei: String?): Boolean {
        // Ensure we have an authenticated user (anonymous if needed)
        authRepository.ensureSignedIn()

        val ser = serial?.trim().orEmpty()
        val im = imei?.trim().orEmpty()

        if (ser.isBlank() && im.isBlank()) return false

        // Try IMEI first if available
        if (im.isNotBlank()) {
            val imeiQuery = collection
                .whereEqualTo("imei", im)
                .limit(1)
                .get()
                .await()
            if (!imeiQuery.isEmpty) return true
        }

        if (ser.isNotBlank()) {
            val serialQuery = collection
                .whereEqualTo("serial", ser)
                .limit(1)
                .get()
                .await()
            if (!serialQuery.isEmpty) return true
        }

        return false
    }
}
