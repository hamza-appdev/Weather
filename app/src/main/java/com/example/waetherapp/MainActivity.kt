package com.example.waetherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.waetherapp.data.repoimp.WeatherRepositoryimp
import com.example.waetherapp.data.services.Weatherapiservices
import com.example.waetherapp.domain.repository.weather.WeatherRepository
import com.example.waetherapp.presentation.WeatherScreen
import com.example.waetherapp.presentation.WeatherViewmodel
import com.example.waetherapp.ui.theme.WaetherAppTheme


class MainActivity : ComponentActivity() {
    private val weatherapiservices: Weatherapiservices by lazy {
        Weatherapiservices()
    }
    private val WeatherRepository: WeatherRepository by lazy {
        WeatherRepositoryimp(weatherapiservices)
    }
    //create instance of weatherviewmodel
    private val viewmodel: WeatherViewmodel by lazy {
        WeatherViewmodel(WeatherRepository)
    }
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

