package com.demate.laucherstupid.examples

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demate.laucherstupid.domain.usecase.GetDeviceIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Exemplo de como usar o GetDeviceIdUseCase em outro ViewModel
 */
@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val getDeviceIdUseCase: GetDeviceIdUseCase
) : ViewModel() {

    private val _deviceId = MutableStateFlow("")
    val deviceId: StateFlow<String> = _deviceId.asStateFlow()

    fun loadDeviceId() {
        viewModelScope.launch {
            val id = getDeviceIdUseCase()
            _deviceId.value = id
        }
    }
}
