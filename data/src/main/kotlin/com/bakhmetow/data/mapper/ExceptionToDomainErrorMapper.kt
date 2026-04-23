package com.bakhmetow.data.mapper

import com.bakhmetow.domain.model.Error
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExceptionToDomainErrorMapper @Inject constructor() {
    fun map(ex: Exception): Error =
        when (ex) {
            is IOException -> Error.IOError(ex.message ?: "Network Error")
            is HttpException -> Error.HttpError(ex.code(), ex.message())
            else -> Error.UnknownError(ex.message ?: "Unknown Error")
        }
}