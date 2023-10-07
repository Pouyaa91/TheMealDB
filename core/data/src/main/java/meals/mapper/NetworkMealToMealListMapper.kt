package meals.mapper

import com.pouyaa.core.network.model.meal.NetworkMeal
import com.pouyaa.model.Meal
import javax.inject.Inject

class NetworkMealToMealListMapper @Inject constructor() {
    fun map(input: List<NetworkMeal>): List<Meal> {
        return input.map {
            Meal(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                imageUrl = it.imageUrl.orEmpty()
            )
        }
    }
}