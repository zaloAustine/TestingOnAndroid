package com.zalo.androidtesting.view.state

import com.zalo.androidtesting.model.Animals

/**
Created by zaloaustine in 9/12/23.
 */
sealed class MainState {
    object Loading : MainState()
    data class Success(val names: List<Animals>) : MainState()
    data class Error(val error :String) : MainState()

    object NoInternet : MainState()
}
