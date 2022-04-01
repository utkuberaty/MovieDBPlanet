package com.base.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val success : Boolean = false,
    @SerialName("status_code") val statusCode: Int,
    @SerialName("status_message") val statusMessage: String,
)
