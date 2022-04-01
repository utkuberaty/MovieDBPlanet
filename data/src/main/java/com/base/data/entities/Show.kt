package com.base.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Show(
    @SerialName("poster_path") val posterPath: String,
    val overview: String,
    @SerialName("original_title") val originalTitle: String = "",
    val title: String = "",
    @SerialName("release_date") val releaseDate: String = "",
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    @SerialName("imdb_id") val imdbId: String = "",
    val id: String
)
