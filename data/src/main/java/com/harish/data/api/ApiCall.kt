package com.harish.data.api

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


suspend fun <T, R> apiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): Result<R> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val mappedData = response.body()?.let { mapper(it) }
            Result.success(mappedData)
        } else {
            Result.failure(Throwable(response.message()))
        }

    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpException -> Result.failure(throwable)
            is IOException -> Result.failure(throwable)
            else -> {
                Result.failure<Exception>(throwable)
            }
        }
    } as Result<R>
}