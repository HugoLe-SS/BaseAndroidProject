package com.example.utilities

sealed class Resource<D, out E : AppError>(
    val data: D? = null,
    val error: E? = null
) {
    class Success<D>(data: D) : Resource<D, AppError>(data = data)
    class Error<D, out E : AppError>(error: E, data: D? = null) : Resource<D, E>(data = data, error = error)
    class Loading<D, out E : AppError>(
        val isFetchingFromNetwork: Boolean = false,
        data: D? = null
    ) : Resource<D, E>(data = data, error = null)
}