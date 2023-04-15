package com.example.composemovies.data.remote

import com.example.composemovies.data.model.MovieDetailsResponse
import com.example.composemovies.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getNowPlayingMovie(
        @Query("api_key") api_key: String = "e6503da7d6f60fc032a4958a91afee62",
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_Id}")
    suspend fun getNowPlayingMovieByPage(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = "e6503da7d6f60fc032a4958a91afee62",
    ): Response<MovieDetailsResponse>

}