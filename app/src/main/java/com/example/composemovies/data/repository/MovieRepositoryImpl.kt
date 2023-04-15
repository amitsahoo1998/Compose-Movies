package com.example.composemovies.data.repository

import com.example.composemovies.domain.repository.Either
import com.example.composemovies.domain.repository.MovieRepository
import com.example.composemovies.data.model.MovieDetailsResponse
import com.example.composemovies.data.model.MovieResponse
import com.example.composemovies.data.model.Movies
import com.example.composemovies.data.remote.MovieApiService
import com.example.composemovies.utils.Constant
import com.example.composemovies.utils.getResponse
import com.example.composemovies.utils.handleResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val movieApiService: MovieApiService
) : MovieRepository {
    override fun getPopularMovies(page: Int): Flow<Either<MovieResponse>> {
        return handleResponse {
            httpClient.get(urlString = Constant.MOVIE_BASE_URL+"movie/popular?api_key=e6503da7d6f60fc032a4958a91afee62&page=$page")
        }
    }

    override fun getPopularMovies2(page: Int): Flow<Either<List<Movies>>> = flow {
        val moviesResponse = movieApiService.getNowPlayingMovie(page = page).getResponse()
        if (moviesResponse.page!=0) emit(Either.success(moviesResponse.results)) else emit(Either.error(""))
    }.catch { it.printStackTrace()
        emit(Either.Error(it.message.toString())) }


    override suspend fun getMovieDetailsById(movieId: Int): Either<MovieDetailsResponse> {
        return runCatching {
            val movieDetailsResponse =
                movieApiService.getNowPlayingMovieByPage(movieId = movieId).getResponse()
            Either.success(movieDetailsResponse)
        }.getOrDefault(Either.error("Something went wrong!"))
    }

}