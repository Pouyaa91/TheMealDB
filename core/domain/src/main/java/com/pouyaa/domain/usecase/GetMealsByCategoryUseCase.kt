package com.pouyaa.domain.usecase

import com.pouyaa.common.result.Result
import com.pouyaa.model.Meal
import kotlinx.coroutines.flow.Flow
import meals.repository.MealsByCategoryRepository
import javax.inject.Inject

class GetMealsByCategoryUseCase @Inject constructor(
    private val mealsByCategoryRepository: MealsByCategoryRepository
) {
    fun fetch(categoryName: String): Flow<Result<List<Meal>>> =
        mealsByCategoryRepository.fetch(categoryName)
}