package com.bakhmetow.domain.model

sealed class Error(val message: String) {
    class HttpError(val code: Int, message: String) : Error(message)
    class IOError(message: String) : Error(message)
    class UnknownError(message: String) : Error(message)
}