package com.zalo.androidtesting.api

import com.zalo.androidtesting.model.Animals


/**
Created by zaloaustine in 9/12/23.
 */
interface AnimalRepository{
    suspend fun getAnimals():List<Animals>
}