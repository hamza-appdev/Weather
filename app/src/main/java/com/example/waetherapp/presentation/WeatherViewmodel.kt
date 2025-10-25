package com.example.waetherapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waetherapp.data.remote.dto.WeatherDto
import com.example.waetherapp.domain.repository.weather.WeatherRepository
import com.example.waetherapp.util.Resultt
import kotlinx.coroutines.launch

class WeatherViewmodel(
    private val weatherRepository: WeatherRepository
): ViewModel() {
    var city by mutableStateOf("")
        private set

    var snackbarmessage by mutableStateOf<String?>(null)
    private set

    fun updatecity(newcity: String){
        city=newcity
    }

    private val _weatherstate = mutableStateOf<Resultt<WeatherDto>>(Resultt.Initial)
    val weatherstate: State<Resultt<WeatherDto>> = _weatherstate

//    var weatherstate by mutableStateOf<Resultt<WeatherDto>>(Resultt.Initial)
//        private set


    fun searchweather(){
        if (city.isBlank()){
            snackbarmessage = "Please enter a city name"
            return
        }
        getdataforcity(city)
    }

    private fun getdataforcity(city: String){
        viewModelScope.launch {
            _weatherstate.value = Resultt.Loading
            try {
                val weatherdata = weatherRepository.getWeather(city)
                _weatherstate.value = Resultt.Success(weatherdata)
            }catch (e: Exception){
                val errormessage = e.localizedMessage?:"Unknown Error occured"
                _weatherstate.value = Resultt.Error(errormessage)
                snackbarmessage = errormessage
            }
        }
    }

    fun clearsnackmessage() {
        snackbarmessage = null
    }
}
