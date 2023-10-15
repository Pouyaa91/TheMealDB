package com.pouyaa.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pouyaa.core.network.service.CategoriesApiService
import com.pouyaa.core.network.service.MealInfoApiService
import com.pouyaa.core.network.service.MealsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build()

    @Provides
    @Singleton
    fun provideCategoriesApiService(retrofit: Retrofit): CategoriesApiService =
        retrofit.create(CategoriesApiService::class.java)

    @Provides
    @Singleton
    fun provideMealsApiService(retrofit: Retrofit): MealsApiService =
        retrofit.create(MealsApiService::class.java)

    @Provides
    @Singleton
    fun provideMealInfoApiService(retrofit: Retrofit): MealInfoApiService =
        retrofit.create(MealInfoApiService::class.java)
}