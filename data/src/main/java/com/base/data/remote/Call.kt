package com.base.data.remote

import com.base.data.entities.ApiError
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response

/**
 * Call function for all API calls manages response types returns [Result]
 * */

abstract class Call(private val json: Json) {

    protected suspend fun <T> call(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                Result.Success(body)
            } else errorHandler(response)
        } catch (e: Exception) {
            Result.Error(-1, e.message ?: e.toString())
        }
    }

    private fun <T> errorHandler(response: Response<T>): Result.Error {
        return try {
            val apiError: ApiError = json.decodeFromString(response.errorBody()?.string() ?: "")
            Result.Error(apiError.statusCode, apiError.statusMessage)
        } catch (exception: Exception) {
            Result.Error(response.code(), response.message())
        }
    }
}