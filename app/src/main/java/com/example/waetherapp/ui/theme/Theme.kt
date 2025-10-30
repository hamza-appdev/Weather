package com.example.waetherapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = DeepBlue,
    onPrimary = CloudWhite,
    primaryContainer = NightBlue,
    onPrimaryContainer = CloudWhite,
    secondary = MoonYellow,
    onSecondary = DarkGray,
    secondaryContainer = StormGray,
    onSecondaryContainer = MoonYellow,
    tertiary = RainyBlue,
    background = DarkGray,
    surface = DarkGray,
    surfaceVariant = StormGray
)

private val LightColorScheme = lightColorScheme(
    primary = SkyBlue,
    onPrimary = CloudWhite,
    primaryContainer = LightBlue,
    onPrimaryContainer = DeepBlue,
    secondary = SunnyYellow,
    onSecondary = DarkGray,
    secondaryContainer = LightGray,
    onSecondaryContainer = DarkGray,
    tertiary = RainyBlue,
    background = CloudWhite,
    surface = CloudWhite,
    surfaceVariant = LightGray
)




@Composable
fun WaetherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme ->   DarkColorScheme
        else -> LightColorScheme
    }
    // 👇 System bar control (status & nav bar)
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !darkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (darkTheme) Color.Black else Color.White, // 👈 change status bar color

        )
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}