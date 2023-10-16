package meals.repository

import com.pouyaa.common.result.Result
import com.pouyaa.core.network.service.MealsApiService
import com.pouyaa.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import meals.mapper.NetworkMealToMealListMapper
import javax.inject.Inject

class MealsByCategoryRepositoryImpl @Inject constructor(
    private val apiService: MealsApiService,
    private val mapper: NetworkMealToMealListMapper
) : MealsByCategoryRepository {
    override fun fetch(categoryName: String): Flow<Result<List<Meal>>> {
        return flow<Result<List<Meal>>> {
            val result = apiService.getMealsByCategory(categoryName)
            emit(Result.Success(data = mapper.map(result.mealsList)))
        }.catch {
            emit(Result.Error(it))
        }
    }
}