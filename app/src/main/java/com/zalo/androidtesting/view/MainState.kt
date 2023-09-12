package com.zalo.androidtesting.view

/**
Created by zaloaustine in 9/12/23.
 */

sealed class MainState {
    object Idle:MainState()
    object Loading:MainState()
    data class Animals(val animals:List<com.zalo.androidtesting.model.Animals>):MainState()
    data class Error(val error:String):MainState()
}