package com.pouyaa.data.mealinfo.di

import com.pouyaa.data.mealinfo.repository.MealInfoRepository
import com.pouyaa.data.mealinfo.repository.MealInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MealInfoModule {
    @Binds
    fun provideMealInfoRepository(impl: MealInfoRepositoryImpl): MealInfoRepository
}