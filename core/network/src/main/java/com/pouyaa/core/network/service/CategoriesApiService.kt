package com.pouyaa.core.network.service

import com.pouyaa.core.network.model.category.NetworkCategoriesWrapper
import retrofit2.http.GET

interface CategoriesApiService {
    @GET("categories.php")
    suspend fun getCategories(): NetworkCategoriesWrapper
}