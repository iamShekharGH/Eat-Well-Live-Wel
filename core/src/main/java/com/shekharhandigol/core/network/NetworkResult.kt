package com.shekharhandigol.core.network

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Failure(val code: Int, val message: String) : NetworkResult<Nothing>
    data object NetworkError : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}

sealed interface UiLoadState<out T> {
    data class Success<T>(val data: T) : UiLoadState<T>
    data object Failure : UiLoadState<Nothing>
    data object Loading : UiLoadState<Nothing>
}
