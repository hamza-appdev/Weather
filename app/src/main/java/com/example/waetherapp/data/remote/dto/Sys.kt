package com.example.waetherapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Sys(
    val country: String,
    val sunrise: Int,
    val sunset: Int
)