package com.zalo.androidtesting.api

import com.zalo.androidtesting.model.Animals
import javax.inject.Inject


/**
Created by zaloaustine in 9/12/23.
 */

class AnimalRepositoryImpl @Inject constructor(private val api: AnimalApi):AnimalRepository{
    override suspend fun getAnimals(): List<Animals> {
        return api.getAnimals()
    }

}