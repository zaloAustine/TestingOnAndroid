package com.zalo.androidtesting.di

import com.zalo.androidtesting.api.AnimalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
Created by zaloaustine in 9/12/23.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/CatalinStefan/animalApi/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimalApiService(retrofit: Retrofit):AnimalApi{
        return retrofit.create(AnimalApi::class.java)
    }
}