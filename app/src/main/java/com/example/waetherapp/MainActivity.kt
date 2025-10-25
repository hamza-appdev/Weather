package com.example.waetherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import com.example.waetherapp.presentation.WeatherScreen
import com.example.waetherapp.presentation.WeatherViewmodel
import com.example.waetherapp.ui.theme.WaetherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   private val viewmodel: WeatherViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaetherAppTheme {
                WeatherScreen(viewModel = viewmodel)
            }
        }
    }
}

