package com.example.waetherapp.di

import com.example.waetherapp.data.repoimp.WeatherRepositoryimp
import com.example.waetherapp.data.services.Weatherapiservices
import com.example.waetherapp.domain.repository.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule { //implementation
    @Provides
    @Singleton
    fun provideWeatherRepository(
      apiServices: Weatherapiservices
    ): WeatherRepository{
        return WeatherRepositoryimp(apiServices)
    }
}