package com.pouyaa.core.network.model.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCategoriesWrapper(
    @SerialName(value = "categories") val categories: List<NetworkCategory>
)