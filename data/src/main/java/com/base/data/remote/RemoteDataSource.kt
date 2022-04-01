package com.base.data.remote

class RemoteDataSource(private val movieDBPlanetService: MovieDBPlanetService) : Call() {

    suspend fun nowPlayingShowList() = call { movieDBPlanetService.nowPlayingShowList() }
    suspend fun upComingShowList() = call { movieDBPlanetService.upComingShowList() }
    suspend fun showDetail(id: String) = call { movieDBPlanetService.showDetail(id) }

}