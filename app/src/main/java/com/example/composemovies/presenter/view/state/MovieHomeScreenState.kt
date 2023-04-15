package com.example.composemovies.presenter.view.state

import com.example.composemovies.data.model.MovieResponse
import com.example.composemovies.data.model.Movies

data class MovieHomeScreenState(
    val isLoading: Boolean = false,
    val error : String? = null,
    val movieList: List<Movies> = emptyList()
) : State