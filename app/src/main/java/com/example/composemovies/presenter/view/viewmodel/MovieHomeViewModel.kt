package com.example.composemovies.presenter.view.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.composemovies.domain.repository.MovieRepository
import com.example.composemovies.presenter.di.RemoteRepository
import com.example.composemovies.presenter.view.state.MovieHomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    @RemoteRepository private val repository: MovieRepository
) : BaseViewModel<MovieHomeScreenState>(initialState = MovieHomeScreenState()) {

    init {
        repository.getPopularMovies(page = 1)
            .distinctUntilChanged()
            .onEach { response ->
                response.onSuccess { movies ->
                    setState {
                        it.copy(
                            isLoading = false,
                            movieList = movies.results
                        )
                    }
                }.onFailure { message ->
                    setState { state ->
                        state.copy(
                            isLoading = false,
                            error = message
                        )
                    }
                }
            }.onStart { setState { it.copy(isLoading = true) } }
            .launchIn(viewModelScope)
    }

    fun apicall(){
        repository.getPopularMovies2(page = 2)
            .distinctUntilChanged()
            .onEach { response ->
                response.onSuccess { movies ->
                    setState {
                        it.copy(
                            isLoading = false,
                            movieList = movies
                        )
                    }
                }.onFailure { message ->
                    setState { state ->
                        state.copy(
                            isLoading = false,
                            error = message
                        )
                    }
                }
            }.onStart { setState { it.copy(isLoading = true) } }
            .launchIn(viewModelScope)
    }

}