package com.utku.moviedbplanet.di

import com.base.data.remote.RemoteDataSource
import com.base.data.repository.MovieDBPlanetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Hilt singleton Component module for repositories
 * */

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun movieDBPlanetRepository(
        remoteDataSource: RemoteDataSource
    ): MovieDBPlanetRepository = MovieDBPlanetRepository(remoteDataSource)
}