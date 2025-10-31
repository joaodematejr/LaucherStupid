package com.demate.laucherstupid.ui.screens.home

data class HomeUiState(
    val deviceSerial: String = "...",
    val deviceImei: String = "",
    val isLoading: Boolean = true,
    val isVerifying: Boolean = false,
    val errorMessage: String? = null
)
