package com.example.composemvvm.common

sealed class ResultWrapper<out T> {

    data class Success<out T>(val value: T) : ResultWrapper<T>()

    data class GenericError(val code: Int? = null, val message: String? = null) :
        ResultWrapper<Nothing>()

    data class NetworkError(val message: String) : ResultWrapper<Nothing>()
}