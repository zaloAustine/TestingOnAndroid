package com.zalo.androidtesting.api

import com.zalo.androidtesting.model.Animals
import retrofit2.http.GET

/**
Created by zaloaustine in 9/12/23.
 */

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals():List<Animals>
}