package com.zalo.androidtesting.view.presentation

import android.util.Log
import com.zalo.androidtesting.api.AnimalRepository
import com.zalo.androidtesting.base.BaseViewModel
import com.zalo.androidtesting.view.state.MainAction
import com.zalo.androidtesting.view.state.MainIntent
import com.zalo.androidtesting.view.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
Created by zaloaustine in 9/12/23.
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AnimalRepository) :
    BaseViewModel<MainIntent, MainState>() {

    override val initialState: MainState = MainState.Loading.also { updateViewState(it) }

    override suspend fun processIntent(intent: MainIntent) {
        Log.e("intent", intent.toString())
        return when (intent) {
            is MainIntent.fetchAnimals -> {
                fetchAnimals()
            }
        }
    }

    private suspend fun fetchAnimals() {
        return try {
            updateViewState(MainState.Loading)
            val animals = repository.getAnimals()
            updateViewState(MainState.Success(animals))
        } catch (e: Exception) {
            updateViewState(MainState.Error(e.localizedMessage.toString()))
        }
    }

}