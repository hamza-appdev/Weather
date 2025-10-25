package com.example.waetherapp.data.services

import io.ktor.client.HttpClient

import javax.inject.Inject

class Weatherapiservices @Inject constructor(httpClient: HttpClient) {
    val client= httpClient
}