package com.demate.laucherstupid.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Cores específicas para funcionalidades do Launcher de Pagamento
 * Essas cores complementam o tema Material 3 para casos específicos
 */
@Immutable
data class LauncherColors(
    // ========== TRANSAÇÕES E PAGAMENTOS ==========
    val transactionSuccess: Color,
    val transactionPending: Color,
    val transactionFailed: Color,
    val transactionRefund: Color,
    val cardChip: Color,
    val cardMagnetic: Color,
    val cardContactless: Color,

    // ========== SISTEMA E HARDWARE ==========
    val systemOnline: Color,
    val systemOffline: Color,
    val systemProcessing: Color,
    val batteryFull: Color,
    val batteryMedium: Color,
    val batteryLow: Color,
    val batteryCritical: Color,

    // ========== COMUNICAÇÃO ==========
    val wifiConnected: Color,
    val wifiDisconnected: Color,
    val bluetoothConnected: Color,
    val bluetoothDisconnected: Color,
    val gprsConnected: Color,
    val gprsDisconnected: Color,
    val gpsActive: Color,
    val gpsInactive: Color,

    // ========== INTERFACE ==========
    val splashGradientStart: Color,
    val splashGradientEnd: Color,
    val cardElevation: Color,
    val divider: Color,
    val shimmer: Color,
    val overlay: Color,

    // ========== ESPECIFICAÇÕES TÉCNICAS ==========
    val processorNormal: Color,
    val processorHigh: Color,
    val memoryAvailable: Color,
    val memoryUsed: Color,
    val displayActive: Color,
    val displayInactive: Color
)

/**
 * Cores para tema claro
 */
val lightLauncherColors = LauncherColors(
    // Transações
    transactionSuccess = SuccessGreen,
    transactionPending = WarningYellow,
    transactionFailed = ErrorRed,
    transactionRefund = InfoCyan,
    cardChip = Color(0xFFFFD700),        // Dourado para chip
    cardMagnetic = NeutralGray800,       // Cinza escuro para tarja
    cardContactless = SystemBlue,        // Azul para contactless

    // Sistema
    systemOnline = SuccessGreen,
    systemOffline = ErrorRed,
    systemProcessing = WarningYellow,
    batteryFull = BatteryGreen,
    batteryMedium = WarningYellow,
    batteryLow = AccentOrange,
    batteryCritical = ErrorRed,

    // Comunicação
    wifiConnected = SuccessGreen,
    wifiDisconnected = NeutralGray400,
    bluetoothConnected = CommunicationPurple,
    bluetoothDisconnected = NeutralGray400,
    gprsConnected = InfoCyan,
    gprsDisconnected = NeutralGray400,
    gpsActive = SuccessGreen,
    gpsInactive = NeutralGray400,

    // Interface
    splashGradientStart = PrimaryBlue,
    splashGradientEnd = PrimaryBlueLight,
    cardElevation = NeutralGray200,
    divider = NeutralGray200,
    shimmer = NeutralGray200,
    overlay = Color.Black.copy(alpha = 0.5f),

    // Especificações
    processorNormal = SuccessGreen,
    processorHigh = WarningYellow,
    memoryAvailable = SuccessGreen,
    memoryUsed = AccentOrange,
    displayActive = SystemBlue,
    displayInactive = NeutralGray400
)

/**
 * Cores para tema escuro
 */
val darkLauncherColors = LauncherColors(
    // Transações
    transactionSuccess = SuccessGreenLight,
    transactionPending = WarningYellowLight,
    transactionFailed = ErrorRedLight,
    transactionRefund = InfoCyanLight,
    cardChip = Color(0xFFFFD700),        // Dourado para chip
    cardMagnetic = NeutralGray300,       // Cinza claro para tarja
    cardContactless = PrimaryBlueLight,  // Azul claro para contactless

    // Sistema
    systemOnline = SuccessGreenLight,
    systemOffline = ErrorRedLight,
    systemProcessing = WarningYellowLight,
    batteryFull = BatteryGreen,
    batteryMedium = WarningYellowLight,
    batteryLow = AccentOrangeLight,
    batteryCritical = ErrorRedLight,

    // Comunicação
    wifiConnected = SuccessGreenLight,
    wifiDisconnected = NeutralGray500,
    bluetoothConnected = CommunicationPurple,
    bluetoothDisconnected = NeutralGray500,
    gprsConnected = InfoCyanLight,
    gprsDisconnected = NeutralGray500,
    gpsActive = SuccessGreenLight,
    gpsInactive = NeutralGray500,

    // Interface
    splashGradientStart = PrimaryBlue,
    splashGradientEnd = PrimaryBlueLight,
    cardElevation = NeutralGray700,
    divider = NeutralGray600,
    shimmer = NeutralGray600,
    overlay = Color.Black.copy(alpha = 0.7f),

    // Especificações
    processorNormal = SuccessGreenLight,
    processorHigh = WarningYellowLight,
    memoryAvailable = SuccessGreenLight,
    memoryUsed = AccentOrangeLight,
    displayActive = PrimaryBlueLight,
    displayInactive = NeutralGray500
)

/**
 * CompositionLocal para acessar as cores do launcher
 */
val LocalLauncherColors = staticCompositionLocalOf { lightLauncherColors }

/**
 * Extensão para acessar as cores facilmente
 */
val ColorScheme.launcher: LauncherColors
    @Composable get() = LocalLauncherColors.current
