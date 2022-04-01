package com.utku.moviedbplanet.di

import com.base.data.remote.MovieDBPlanetService
import com.base.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Hilt singleton Component module for data sources
 * */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun remoteDataSource(movieDBPlanetService: MovieDBPlanetService, json: Json) =
        RemoteDataSource(movieDBPlanetService, json)
}