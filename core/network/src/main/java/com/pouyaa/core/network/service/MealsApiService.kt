package com.pouyaa.core.network.service

import com.pouyaa.core.network.model.meal.NetworkMealsWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService {

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): NetworkMealsWrapper
}