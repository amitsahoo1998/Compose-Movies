package com.example.composemovies.domain.repository

import com.example.composemovies.data.model.MovieDetailsResponse
import com.example.composemovies.data.model.MovieResponse
import com.example.composemovies.data.model.Movies
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MovieRepository {

    /**
     * Returns all Popular Movies.(Ktor Client)
     */
    fun getPopularMovies(page: Int = 1): Flow<Either<MovieResponse>>



    fun getPopularMovies2(page: Int = 1): Flow<Either<List<Movies>>>

    /**
     *  Return A Movie Details By [movieId]
     *  @param movieId A Movie Id
     */
    suspend fun getMovieDetailsById(movieId: Int): Either<MovieDetailsResponse>
}