package com.pouyaa.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCategoriesWrapper(
    @SerialName(value = "categories") val categories: List<NetworkCategory>
)