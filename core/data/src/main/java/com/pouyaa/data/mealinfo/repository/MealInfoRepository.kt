package com.pouyaa.data.mealinfo.repository

import com.pouyaa.common.result.Result
import com.pouyaa.model.Meal
import kotlinx.coroutines.flow.Flow

fun interface MealInfoRepository {
    fun fetch(id: String): Flow<Result<Meal>>
}