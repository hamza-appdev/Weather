package com.example.waetherapp.domain.repository.weather

import com.example.waetherapp.data.remote.dto.WeatherDto

interface WeatherRepository {
    suspend fun getWeather(city: String): WeatherDto
}