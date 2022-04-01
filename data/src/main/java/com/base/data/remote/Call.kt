package com.base.data.remote

import retrofit2.Response

/**
 * Call function for all API calls manages response types returns [Result]
 * */

abstract class Call {

    protected suspend fun <T> call(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Result.Success(body)
            } else Result.Error(response.code(), response.message())
        } catch (e: Exception) {
            Result.Error(-1, e.message ?: e.toString())
        }
    }
}