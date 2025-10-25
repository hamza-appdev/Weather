package com.example.waetherapp.util

sealed class Resultt<out T> {
    object Initial: Resultt<Nothing>()
    object Loading: Resultt<Nothing>()
    data class Success<out T>(val data:T): Resultt<T>()
    data class Error(val message: String): Resultt<Nothing>()
}