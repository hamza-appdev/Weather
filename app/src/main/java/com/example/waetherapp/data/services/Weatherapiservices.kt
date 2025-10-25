package com.example.waetherapp.data.services

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol

import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json


class Weatherapiservices (){

    val client = HttpClient(CIO) {
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys=true
                isLenient=true
            })
        }
        install(HttpTimeout){
            socketTimeoutMillis=3000
            requestTimeoutMillis=3000
            connectTimeoutMillis=3000
        }
        install(DefaultRequest){
            url{
                host= "api.openweathermap.org"
                protocol = URLProtocol.HTTPS
            }
        }

       install(Logging){
          logger = Logger.ANDROID
           level= LogLevel.ALL

       }
    }

}