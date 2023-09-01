package com.pouyaa.data.categories.di

import com.pouyaa.data.categories.repository.CategoriesRepository
import com.pouyaa.data.categories.repository.CategoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CategoriesModule {
    @Binds
    fun provideCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository
}