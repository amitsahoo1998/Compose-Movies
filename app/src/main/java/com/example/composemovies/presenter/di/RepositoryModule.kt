package com.example.composemovies.presenter.di

import com.example.composemovies.data.repository.MovieRepositoryImpl
import com.example.composemovies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @RemoteRepository
    fun movieRemoteRepository(repository: MovieRepositoryImpl): MovieRepository

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteRepository