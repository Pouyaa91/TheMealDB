package com.pouyaa.core.network.service

import com.pouyaa.core.network.model.meal.NetworkMealsWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MealInfoApiService {

    @GET("lookup.php")
    suspend fun getMealInfo(@Query("i") id: String): NetworkMealsWrapper
}