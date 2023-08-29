package com.pouyaa.core.network.di

import com.pouyaa.core.network.service.CategoriesApiService
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Wrapped.ADAPTER_FACTORY)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build()

    @Provides
    @Singleton
    fun provideCategoriesApiService(retrofit: Retrofit): CategoriesApiService =
        retrofit.create(CategoriesApiService::class.java)
}