package com.pouyaa.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMealsWrapper(
    @SerialName(value = "meals") val mealsList: List<NetworkMeal>
)
