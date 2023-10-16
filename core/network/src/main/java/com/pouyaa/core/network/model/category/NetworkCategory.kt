package com.pouyaa.core.network.model.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCategory(
    @SerialName(value = "idCategory") val id: String?,
    @SerialName(value = "strCategory") val name: String?,
    @SerialName(value = "strCategoryThumb") val imageUrl: String?,
    @SerialName(value = "strCategoryDescription") val description: String?
)