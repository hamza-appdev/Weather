package com.example.waetherapp.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.copy
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.waetherapp.ui.theme.CloudWhite
import com.example.waetherapp.ui.theme.DarkNightBlue
import com.example.waetherapp.ui.theme.DeepSpaceBlue
import com.example.waetherapp.ui.theme.LightBlue
import com.example.waetherapp.ui.theme.MidnightGray
import com.example.waetherapp.ui.theme.SkyBlue
import kotlinx.serialization.json.JsonNull.content

@Composable
fun Animatedweatherbackground(
    modifier: Modifier= Modifier,
    content:@Composable () -> Unit
) {
    val isDark = androidx.compose.foundation.isSystemInDarkTheme()
    val infiniteTransition = rememberInfiniteTransition(label = "Weather background transistation")
    val coloranimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color_animation"
    )

    val gradientColors = if (isDark) {
        listOf(
            DeepSpaceBlue.copy(alpha = 0.7f + (coloranimation * 0.2f)),
            DarkNightBlue.copy(alpha = 0.7f + (coloranimation * 0.15f)),
            DeepSpaceBlue.copy(alpha = 0.6f + (coloranimation * 0.2f)),
        )
    } else {
        listOf(
            SkyBlue.copy(alpha = 0.7f + (coloranimation  * 0.2f)),
            LightBlue.copy(alpha = 0.9f + (coloranimation  * 0.3f)),
            CloudWhite.copy(alpha = 0.7f)
        )
    }

    Box (
        modifier= Modifier.fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                colors = gradientColors

                )
            )
    ){
        content()
    }

}