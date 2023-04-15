package com.example.composemovies.presenter.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composemovies.presenter.view.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet

abstract class BaseViewModel<STATE : State>(initialState: STATE) : ViewModel() {

    /**
     * Mutable State of this ViewModel
     */
    private val _state = MutableStateFlow(initialState)

    /**
     * State to exposed to UI layer
     */
    val state: StateFlow<STATE> = _state.asStateFlow()

    /**
     * Retrieve the current state
     */
    val currentState: STATE get() = state.value

    /**
     *  Update the state of this ViewModel and returns the new state
     * @param update Lambda callback with old state to calculate a new state
     * @return The updated state
     */
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)


}