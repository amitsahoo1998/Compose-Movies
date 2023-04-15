package com.example.composemovies.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composemovies.presenter.view.state.State
import com.example.composemovies.presenter.view.viewmodel.BaseViewModel


@Composable
fun <S : State , VM : BaseViewModel<S>> VM.collectState() = state.collectAsStateWithLifecycle()