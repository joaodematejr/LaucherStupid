package com.demate.laucherstupid.domain.repository

interface DeviceRepository {
    suspend fun getDeviceSerial(): String
}
