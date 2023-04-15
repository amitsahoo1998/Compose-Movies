package com.example.composemovies.presenter.ui

sealed class Screen(val route: String , val name: String){

    object MovieHome : Screen("home" , "Home")

    object MovieDetails : Screen("movie/{movieId}" , "MovieDetails"){

        fun route(movieId : Int) = "movie/$movieId"

        const val ARG_MOVIE_ID : String = "movieId"
    }
}
