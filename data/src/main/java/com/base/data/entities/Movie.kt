package com.base.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("poster_path") val posterPath: String = "",
    val overview: String = "",
    @SerialName("original_title") val originalTitle: String = "",
    val title: String = "",
    @SerialName("release_date") val releaseDate: String = "",
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    @SerialName("imdb_id") val imdbId: String = "",
    @SerialName("backdrop_path") val backdropPath: String = "",
    val id: String
) {
    val titleWithYear
    get() = "$title(${releaseDate.split("-").first()})"

    val modifiedReleaseDate
    get() = releaseDate.replace("-", ".")
}

enum class MovieType {
    UP_COMING, NOW_PLAYING
}