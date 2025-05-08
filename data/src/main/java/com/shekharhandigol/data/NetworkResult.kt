package com.shekharhandigol.data


sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure(val code: Int, val message: String) : NetworkResult<Nothing>()
    data object NetworkError : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()
}