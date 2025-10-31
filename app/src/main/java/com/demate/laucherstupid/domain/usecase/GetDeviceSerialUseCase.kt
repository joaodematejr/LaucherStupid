package com.demate.laucherstupid.domain.usecase

import com.demate.laucherstupid.domain.repository.DeviceRepository
import javax.inject.Inject

class GetDeviceSerialUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    suspend operator fun invoke(): String {
        return deviceRepository.getDeviceSerial()
    }
}
