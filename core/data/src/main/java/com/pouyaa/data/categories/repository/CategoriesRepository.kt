package com.pouyaa.data.categories.repository

import com.pouyaa.model.Category
import kotlinx.coroutines.flow.Flow

fun interface CategoriesRepository {
    fun fetch(): Flow<List<Category>>
}