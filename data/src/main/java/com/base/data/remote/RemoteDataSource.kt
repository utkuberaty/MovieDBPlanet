package com.base.data.remote

import kotlinx.serialization.json.Json

class RemoteDataSource(private val movieDBPlanetService: MovieDBPlanetService, json: Json) :
    Call(json) {

    suspend fun nowPlayingShowList() = call { movieDBPlanetService.nowPlayingShowList() }
    suspend fun upComingShowList() = call { movieDBPlanetService.upComingShowList() }
    suspend fun showDetail(id: String) = call { movieDBPlanetService.showDetail(id) }

}