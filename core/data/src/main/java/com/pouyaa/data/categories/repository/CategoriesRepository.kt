package com.pouyaa.data.categories.repository

import com.pouyaa.common.result.Result
import com.pouyaa.model.Category
import kotlinx.coroutines.flow.Flow

fun interface CategoriesRepository {
    fun fetch(): Flow<Result<List<Category>>>
}