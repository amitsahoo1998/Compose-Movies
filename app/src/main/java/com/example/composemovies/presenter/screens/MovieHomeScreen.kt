package com.example.composemovies.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemovies.data.model.MovieResponse
import com.example.composemovies.data.model.Movies
import com.example.composemovies.presenter.component.MovieCard
import com.example.composemovies.presenter.view.viewmodel.MovieHomeViewModel
import com.example.composemovies.utils.collectState


@Composable
fun MovieHomeScreen(
    viewModel: MovieHomeViewModel,
    onNavigateToMovieDetails: () -> Unit
) {
    val state by viewModel.collectState()

    HomeScreenContent(movies = state.movieList , viewModel::apicall)

}

@Composable
fun HomeScreenContent(movies: List<Movies>, apiClick : ()-> Unit) {
    Column {
        Text(text = "Recommended Movies", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 18.dp, horizontal = 24.dp ).clickable { apiClick.invoke() })
        MovieList(movies)
    }
}

@Composable
fun MovieList(movies: List<Movies>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 13.dp),
        modifier = Modifier.testTag("movie list")
    ) {
        items(items = movies,
            itemContent = { movie ->
                MovieCard(
                    moviePoster = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                    title = movie.title!!,
                    overview = movie.overview!!,
                    vote_average = movie.vote_average!!,
                    vote_count = movie.vote_count!!
                )
            })
    }
}
