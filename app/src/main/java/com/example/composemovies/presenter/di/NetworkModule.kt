package com.example.composemovies.presenter.di

import com.example.composemovies.data.remote.MovieApiService
import com.example.composemovies.utils.Constant
import com.example.composemovies.utils.moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val baseRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.MOVIE_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))

    private val okHttpClientBuilder: OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

    @Provides
    fun provideMovieApiService(): MovieApiService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.build())
            .build()
            .create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        val json = Json {
            encodeDefaults= true
            ignoreUnknownKeys = true
            isLenient = true
        }

        return HttpClient(CIO) {
            //install(WebSockets)
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
            install(HttpTimeout){
                socketTimeoutMillis = 60000
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

            install(Logging){
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}