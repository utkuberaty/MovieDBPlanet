package com.base.data.repository

import com.base.data.remote.RemoteDataSource
import com.base.data.util.PerformOperation

class MovieDBPlanetRepository(private val remoteDataSource: RemoteDataSource) {

    fun nowPlayingShowList() = PerformOperation.performGetOperation(
        networkCall = { remoteDataSource.nowPlayingShowList() }
    )

    fun upComingShowList() = PerformOperation.performGetOperation(
        networkCall = { remoteDataSource.upComingShowList() }
    )

    fun showDetail(id: String) = PerformOperation.performGetOperation(
        networkCall = { remoteDataSource.showDetail(id) }
    )
}