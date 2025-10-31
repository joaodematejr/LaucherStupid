package com.demate.laucherstupid.domain.usecase

import com.demate.laucherstupid.data.repository.CloudDeviceRepository
import javax.inject.Inject

class CheckDeviceRegisteredUseCase @Inject constructor(
    private val cloudDeviceRepository: CloudDeviceRepository
) {
    suspend operator fun invoke(serial: String?, imei: String?): Boolean {
        return cloudDeviceRepository.isDeviceRegistered(serial, imei)
    }
}

