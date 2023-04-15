package com.example.composemovies.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemovies.presenter.screens.MovieHomeScreen
import com.example.composemovies.presenter.ui.Screen


const val MOVIE_NAV_HOST_ROUTE = "app-main-route"

@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Screen.MovieHome.route,
        route = MOVIE_NAV_HOST_ROUTE
    ) {
        composable(Screen.MovieHome.route) {
            MovieHomeScreen(viewModel = hiltViewModel(),
                onNavigateToMovieDetails = {})
        }
    }
}