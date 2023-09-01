package com.pouyaa.data.categories.mapper

import com.pouyaa.core.network.model.NetworkCategory
import com.pouyaa.model.Category
import javax.inject.Inject

class NetworkCategoryToCategoryListMapper @Inject constructor() {
    fun map(input: List<NetworkCategory>): List<Category> {
        return input.map {
            Category(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                imageUrl = it.imageUrl.orEmpty(),
                description = it.description.orEmpty()
            )
        }
    }
}