package com.pouyaa.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMeal(
    @SerialName(value = "idMeal") val id: String?,
    @SerialName(value = "strMeal") val name: String?,
    @SerialName(value = "strMealThumb") val imageUrl: String?
)
