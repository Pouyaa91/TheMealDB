package com.pouyaa.data.mealinfo.repository

import com.pouyaa.common.result.Result
import com.pouyaa.core.network.service.MealInfoApiService
import com.pouyaa.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import meals.mapper.NetworkMealToMealListMapper
import javax.inject.Inject

class MealInfoRepositoryImpl @Inject constructor(
    private val apiService: MealInfoApiService,
    private val mapper: NetworkMealToMealListMapper
) : MealInfoRepository {
    override fun fetch(id: String): Flow<Result<Meal>> {
        return flow<Result<Meal>> {
            val result = apiService.getMealInfo(id)
            emit(Result.Success(data = mapper.map(result.mealsList).first()))
        }.catch {
            emit(Result.Error(it))
        }
    }
}