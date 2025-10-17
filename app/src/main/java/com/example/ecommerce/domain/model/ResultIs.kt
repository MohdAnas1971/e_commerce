package com.example.ecommerce.domain.model

sealed class ResultIs<out T> {
    data object Idle: ResultIs<Nothing>()
    data object Loading: ResultIs<Nothing>()
    data class Success<T>(val data: T): ResultIs<T>()
    data class Error(val message: String): ResultIs<Nothing>()
}