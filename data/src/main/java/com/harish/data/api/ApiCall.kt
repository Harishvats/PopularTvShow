package com.harish.data.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


suspend fun <T, R> apiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R,
    ioDispatcher: CoroutineDispatcher
): Result<R> =
    withContext(ioDispatcher) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val mappedData = response.body()?.let { mapper(it) }
                Result.success(mappedData)
            } else {
                Result.failure(Exception(response.message()))
            }

        } catch (exception: Exception) {
            when (exception) {
                is HttpException -> Result.failure(exception)
                is IOException -> Result.failure(exception)
                else -> {
                    Result.failure(exception)
                }
            }
        } as Result<R>
    }
