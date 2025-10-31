package com.demate.laucherstupid.domain.usecase

import com.demate.laucherstupid.data.repository.DeviceRepository
import javax.inject.Inject

class GetDeviceIdUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    operator fun invoke(
        type: DeviceRepository.DeviceIdType = DeviceRepository.DeviceIdType.ANDROID_ID
    ): String {
        return deviceRepository.getDeviceId(type)
    }
}
