package com.base.data.remote

import com.base.data.entities.ShowListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBPlanetService {

    @GET("now_playing")
    suspend fun nowPlayingShowList(): Response<ShowListResponse>

    @GET("upcoming")
    suspend fun upComingShowList(@Query("page") page: Int): Response<ShowListResponse>

    @GET("{movie_id}")
    suspend fun showDetail(@Path("movie_id") moveId: String): Response<ShowListResponse>

}