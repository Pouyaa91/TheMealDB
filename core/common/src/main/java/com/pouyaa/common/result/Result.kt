package com.pouyaa.common.result

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error (val throwable: Throwable?) : Result<Nothing>
}