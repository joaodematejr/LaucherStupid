package com.demate.laucherstupid.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.laucherstupid.data.repository.DeviceRepository
import com.demate.laucherstupid.domain.usecase.GetDeviceIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDeviceIdUseCase: GetDeviceIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDeviceId()
    }

    private fun loadDeviceId() {
        viewModelScope.launch {
            try {
                val deviceId = getDeviceIdUseCase()
                val deviceSerial = try {
                    getDeviceIdUseCase(DeviceRepository.DeviceIdType.SERIAL)
                } catch (_: Exception) {
                    ""
                }
                val deviceImei = try {
                    getDeviceIdUseCase(DeviceRepository.DeviceIdType.IMEI)
                } catch (_: Exception) {
                    ""
                }

                _uiState.value = _uiState.value.copy(
                    deviceSerial = deviceSerial.ifBlank { deviceId },
                    deviceImei = deviceImei,
                    isLoading = false
                )
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(
                    deviceSerial = "unknown",
                    deviceImei = "",
                    isLoading = false
                )
            }
        }
    }

    fun refreshImei() {
        viewModelScope.launch {
            try {
                val deviceImei = try {
                    getDeviceIdUseCase(DeviceRepository.DeviceIdType.IMEI)
                } catch (_: Exception) {
                    ""
                }
                _uiState.value = _uiState.value.copy(deviceImei = deviceImei)
            } catch (_: Exception) {
                // ignore failures
            }
        }
    }
}
