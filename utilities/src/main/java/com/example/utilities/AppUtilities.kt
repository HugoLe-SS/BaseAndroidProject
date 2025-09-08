package com.example.utilities

import java.io.IOException

object AppUtilities {

    // --- Reusable Error Mapping Function ---
    fun Throwable.toAppError(): AppError {
        AppLogger.e(message = "Mapping Throwable to AppError: ${this::class.java.simpleName} - ${this.localizedMessage}")
        return when (this) {
            is IOException -> AppError.RemoteError.NO_INTERNET_ERROR // Covers UnknownHostException, SocketTimeoutException etc.
            is retrofit2.HttpException -> { // Or your specific HTTP client's exception
                val specificErrorType = when (this.code()) {
                    400 -> AppError.RemoteError.CLIENT_ERROR // Bad Request
                    401 -> AppError.RemoteError.CLIENT_ERROR // Unauthorized - you might want a more specific AppError.AuthError here
                    403 -> AppError.RemoteError.CLIENT_ERROR // Forbidden
                    404 -> AppError.RemoteError.CLIENT_ERROR // Not Found - or AppError.NotFoundError
                    429 -> AppError.RemoteError.TOO_MANY_REQUESTS_ERROR
                    in 500..599 -> AppError.RemoteError.SERVER_ERROR
                    else -> AppError.RemoteError.UNKNOWN_ERROR
                }
                RemoteErrorWithCode(specificErrorType, this.code(), this.message())
            }
            is com.google.gson.JsonSyntaxException, // Example for serialization error
            is kotlinx.serialization.SerializationException -> AppError.RemoteError.SERIALIZATION_ERROR
            else -> {
                AppLogger.e(message = "Unhandled exception type mapped to UNKNOWN_ERROR: ${this::class.java.name}")
                AppError.RemoteError.UNKNOWN_ERROR
            }
        }
    }

}