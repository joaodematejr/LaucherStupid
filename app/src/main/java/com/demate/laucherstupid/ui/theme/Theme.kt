package com.demate.laucherstupid.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlueLight,
    onPrimary = LightBackground,
    primaryContainer = PrimaryBlue,
    onPrimaryContainer = PrimaryBluePale,
    secondary = SuccessGreenLight,
    onSecondary = LightBackground,
    secondaryContainer = SuccessGreen,
    onSecondaryContainer = SuccessGreenPale,
    tertiary = AccentOrangeLight,
    onTertiary = NeutralGray900,
    tertiaryContainer = AccentOrange,
    onTertiaryContainer = AccentOrangePale,
    background = DarkBackground,
    onBackground = NeutralGray100,
    surface = DarkSurface,
    onSurface = NeutralGray100,
    surfaceVariant = DarkCard,
    onSurfaceVariant = NeutralGray300,
    surfaceContainer = NeutralGray800,
    surfaceContainerHigh = NeutralGray700,
    surfaceContainerHighest = NeutralGray600,
    surfaceContainerLow = NeutralGray900,
    surfaceContainerLowest = NeutralGray900,
    outline = NeutralGray500,
    outlineVariant = NeutralGray700,
    error = ErrorRedLight,
    onError = NeutralGray900,
    errorContainer = ErrorRed,
    onErrorContainer = ErrorRedPale,
    inverseSurface = LightSurface,
    inverseOnSurface = NeutralGray800,
    inversePrimary = PrimaryBlue,
    scrim = NeutralGray900.copy(alpha = 0.32f)
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = LightBackground,
    primaryContainer = PrimaryBluePale,
    onPrimaryContainer = PrimaryBlue,
    secondary = SuccessGreen,
    onSecondary = LightBackground,
    secondaryContainer = SuccessGreenPale,
    onSecondaryContainer = SuccessGreen,
    tertiary = AccentOrange,
    onTertiary = LightBackground,
    tertiaryContainer = AccentOrangePale,
    onTertiaryContainer = AccentOrange,
    background = LightBackground,
    onBackground = NeutralGray900,
    surface = LightSurface,
    onSurface = NeutralGray900,
    surfaceVariant = NeutralGray100,
    onSurfaceVariant = NeutralGray700,
    surfaceContainer = NeutralGray50,
    surfaceContainerHigh = NeutralGray100,
    surfaceContainerHighest = NeutralGray200,
    surfaceContainerLow = LightBackground,
    surfaceContainerLowest = LightBackground,
    outline = NeutralGray400,
    outlineVariant = NeutralGray200,
    error = ErrorRed,
    onError = LightBackground,
    errorContainer = ErrorRedPale,
    onErrorContainer = ErrorRed,
    inverseSurface = NeutralGray800,
    inverseOnSurface = NeutralGray100,
    inversePrimary = PrimaryBlueLight,
    scrim = NeutralGray900.copy(alpha = 0.32f)
)

@Composable
fun LauncherStupidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val launcherColors = if (darkTheme) darkLauncherColors else lightLauncherColors

    CompositionLocalProvider(
        LocalLauncherColors provides launcherColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
