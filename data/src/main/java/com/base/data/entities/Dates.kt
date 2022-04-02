package com.base.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String = "",
    val minimum: String = ""
)
