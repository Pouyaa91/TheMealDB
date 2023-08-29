package com.pouyaa.core.network.service

import com.pouyaa.core.network.model.NetworkCategory
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET

interface CategoriesApiService {
    @GET("categories.php")
    @Wrapped(path = ["categories"])
    suspend fun getCategories(): List<NetworkCategory>
}