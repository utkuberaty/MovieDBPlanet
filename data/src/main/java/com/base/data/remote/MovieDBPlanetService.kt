package com.base.data.remote

import com.base.data.entities.ShowListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBPlanetService {

    @GET("now_playing")
    fun nowPlayingShowList(): Response<ShowListResponse>

    @GET("upcoming")
    fun upComingShowList(): Response<ShowListResponse>

    @GET("{movie_id}")
    fun showDetail(@Path("movie_id") moveId: String): Response<ShowListResponse>

}