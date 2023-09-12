package com.zalo.androidtesting.di

import com.zalo.androidtesting.api.AnimalApi
import com.zalo.androidtesting.api.AnimalRepository
import com.zalo.androidtesting.api.AnimalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by zaloaustine in 9/12/23.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAnimalsRepository(api: AnimalApi): AnimalRepository {
        return AnimalRepositoryImpl(api)
    }
}