package com.bakhmetow.domain.model

sealed interface Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>
    data class Loading<out T>(val data: T? = null) : Resource<T>
    data class Failure<out T>(val error: Error, val data: T? = null) : Resource<T>
}