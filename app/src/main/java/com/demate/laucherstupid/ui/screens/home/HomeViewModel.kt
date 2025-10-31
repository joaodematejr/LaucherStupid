package com.demate.laucherstupid.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.laucherstupid.data.repository.DeviceRepository
import com.demate.laucherstupid.domain.usecase.CheckDeviceRegisteredUseCase
import com.demate.laucherstupid.domain.usecase.GetDeviceIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDeviceIdUseCase: GetDeviceIdUseCase,
    private val checkDeviceRegisteredUseCase: CheckDeviceRegisteredUseCase
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

    fun onStartClicked(onNavigateHome: () -> Unit, onError: (String) -> Unit) {
        val serial = _uiState.value.deviceSerial
        val imei = _uiState.value.deviceImei

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isVerifying = true, errorMessage = null)
            try {
                val registered = checkDeviceRegisteredUseCase(serial, imei)
                if (registered) {
                    _uiState.value = _uiState.value.copy(isVerifying = false)
                    onNavigateHome()
                } else {
                    val msg = "Dispositivo não cadastrado"
                    _uiState.value = _uiState.value.copy(isVerifying = false, errorMessage = msg)
                    onError(msg)
                }
            } catch (e: Exception) {
                val msg = "Erro ao verificar cadastro" + e.message
                _uiState.value = _uiState.value.copy(isVerifying = false, errorMessage = msg)
                onError(msg)
            }
        }
    }
}
