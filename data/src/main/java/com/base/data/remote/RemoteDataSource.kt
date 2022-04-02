package com.base.data.remote

import kotlinx.serialization.json.Json

class RemoteDataSource(private val movieDBPlanetService: MovieDBPlanetService, json: Json) :
    Call(json) {

    suspend fun nowPlayingShowList(page: Int) = call {
        movieDBPlanetService.nowPlayingShowList(page)
    }

    suspend fun upComingShowList(page: Int) = call { movieDBPlanetService.upComingShowList(page) }
    suspend fun showDetail(id: String) = call { movieDBPlanetService.showDetail(id) }

}