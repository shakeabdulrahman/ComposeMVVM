package com.example.composemvvm.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {

                    val message = when (throwable) {
                        is UnknownHostException -> { "No Internet Connection." }
                        else -> { throwable.message }
                    }
                    ResultWrapper.NetworkError(message = message ?: "Something went wrong.")
                }

                is HttpException -> {
                    val code = throwable.code()
                    val message = "Something went wrong. HttpException!"
                    ResultWrapper.GenericError(code, message)
                }

                else -> {
                    throwable.printStackTrace()
                    ResultWrapper.GenericError(null, throwable.message)
                }
            }
        }
    }
}