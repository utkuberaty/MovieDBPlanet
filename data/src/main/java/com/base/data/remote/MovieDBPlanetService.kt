package com.base.data.remote

import com.base.data.entities.Movie
import com.base.data.entities.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBPlanetService {

    @GET("now_playing")
    suspend fun nowPlayingShowList(@Query("page") page: Int): Response<MovieListResponse>

    @GET("upcoming")
    suspend fun upComingShowList(@Query("page") page: Int): Response<MovieListResponse>

    @GET("{movie_id}")
    suspend fun showDetail(@Path("movie_id") moveId: String): Response<Movie>

}