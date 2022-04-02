package com.base.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.base.data.remote.MoviePagingSource
import com.base.data.remote.RemoteDataSource
import com.base.data.util.PerformOperation

class MovieDBPlanetRepository(private val remoteDataSource: RemoteDataSource) {

    fun nowPlayingShowList() = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true),
        pagingSourceFactory = {
            MoviePagingSource(remoteDataSource::nowPlayingShowList)
        }
    )

    fun upComingShowList() = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true),
        pagingSourceFactory = {
            MoviePagingSource(remoteDataSource::upComingShowList)
        }
    )

    fun showDetail(id: String) = PerformOperation.performGetOperation(
        networkCall = { remoteDataSource.showDetail(id) }
    )
}