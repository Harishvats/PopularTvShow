package com.harish.data.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


suspend fun <T, R> apiCall(
    apiCall: suspend () -> T,
    mapper: (T) -> R,
    ioDispatcher: CoroutineDispatcher
): Result<R> =
    withContext(ioDispatcher) {
        try {
            val response = apiCall()
            val mappedData = mapper(response)
            Result.success(mappedData)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
