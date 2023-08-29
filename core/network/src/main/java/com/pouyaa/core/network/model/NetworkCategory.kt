package com.pouyaa.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCategory(
    @Json(name = "idCategory") val id: String?,
    @Json(name = "strCategory") val name: String?,
    @Json(name = "strCategoryThumb") val imageUrl: String?,
    @Json(name = "strCategoryDescription") val description: String?
)