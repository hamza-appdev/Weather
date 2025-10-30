package com.example.waetherapp.data.repoimp

import com.example.waetherapp.BuildConfig
import com.example.waetherapp.data.remote.dto.WeatherDto
import com.example.waetherapp.data.services.Weatherapiservices
import com.example.waetherapp.domain.repository.weather.WeatherRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryimp @Inject constructor(
    private val apiservices: Weatherapiservices
): WeatherRepository{
    val apiKey = BuildConfig.API_KEY

    override suspend fun getWeather(city: String): WeatherDto {
     return try {
//         The .get() function returns a response object (like HttpResponse).
//         .body() converts (or deserializes) that response into a Kotlin object.
         apiservices.client.get("/data/2.5/weather"){
             parameter("q", city)
             parameter("appid",apiKey)
         }.body()
     }catch (e: Exception){
       e.printStackTrace()
         throw Exception("Failed to fetch weather data: ${e.localizedMessage}")
     }

    }
}