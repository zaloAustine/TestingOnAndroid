package com.zalo.androidtesting.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
/**
 * Base class for ViewModels in MVI architecture using Flows.
 *
 * @param I Type of intents that trigger actions.
 * @param S Type of the state that represents the UI state.
 * @param A Type of actions that can be emitted by the ViewModel.
 */
abstract class BaseViewModel<I, S> : ViewModel() {

    private val _intentChannel = Channel<I>(Channel.BUFFERED)
    private val intents = _intentChannel.receiveAsFlow()

    private val _stateFlow = MutableStateFlow(initialState)
    val state: StateFlow<S> = _stateFlow


    protected abstract val initialState: S

    init {
        viewModelScope.launch {
            handleIntents()
        }
    }

    private suspend fun handleIntents() {
        intents.collect { intent ->
            processIntent(intent)
        }
    }

    protected abstract suspend fun processIntent(intent: I)

    protected fun updateViewState(state: S) {
        _stateFlow.value = state
    }

    fun sendIntent(intent: I) {
        viewModelScope.launch {
            _intentChannel.send(intent)
        }
    }
}