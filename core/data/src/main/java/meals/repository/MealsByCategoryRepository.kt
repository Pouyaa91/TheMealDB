package meals.repository

import com.pouyaa.common.result.Result
import com.pouyaa.model.Meal
import kotlinx.coroutines.flow.Flow

fun interface MealsByCategoryRepository {
    fun fetch(categoryName: String): Flow<Result<List<Meal>>>
}