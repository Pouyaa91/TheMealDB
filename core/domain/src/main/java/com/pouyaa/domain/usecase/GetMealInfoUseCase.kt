package com.pouyaa.domain.usecase

import com.pouyaa.common.result.Result
import com.pouyaa.data.mealinfo.repository.MealInfoRepository
import com.pouyaa.model.Meal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealInfoUseCase @Inject constructor(
    private val mealInfoRepository: MealInfoRepository
) {
    fun fetch(categoryName: String): Flow<Result<Meal>> =
        mealInfoRepository.fetch(categoryName)
}