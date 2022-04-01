package com.utku.moviedbplanet.di

import android.content.Context
import com.base.data.remote.MovieDBPlanetService
import com.base.data.util.ACCESS_TOKEN
import com.base.data.util.BASE_URL
import com.base.data.util.NoConnectionInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Hilt singleton Component module for remote
 * */
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * This is kotlin serialization, use for convert json to data class
     * or data class to json
     * */

    @Provides
    @Singleton
    fun json(): Json = Json {
        prettyPrint = true
        isLenient = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    /**
     * Http client to connect API
     * All TimeOuts 20 seconds
     * Uses [HttpLoggingInterceptor] to log responses or requests
     * */

    @Provides
    @Singleton
    fun noConnectionInterceptor(@ApplicationContext appContext: Context): NoConnectionInterceptor =
        NoConnectionInterceptor(appContext)

    @Provides
    @Singleton
    fun okhttpClient(noConnectionInterceptor: NoConnectionInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                return@addInterceptor chain.proceed(
                    chain.request().newBuilder().url(
                        chain.request()
                            .url
                            .newBuilder()
                            .addQueryParameter("api_key", ACCESS_TOKEN)
                            .build()
                    ).build()
                )
            }
            .addInterceptor(noConnectionInterceptor)
            .build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun planetService(retrofit: Retrofit): MovieDBPlanetService =
        retrofit.create(MovieDBPlanetService::class.java)


}