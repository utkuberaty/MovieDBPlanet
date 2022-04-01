package com.base.data.remote

/**
 * Sealed class includes [Success] and [Error] data classes
 * to manage api call response
 * */

sealed class Result<out T> {
    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val code: Int, val exception: String?): Result<Nothing>()
}