package meals.mapper

import com.pouyaa.core.network.model.meal.NetworkMeal
import com.pouyaa.model.Ingredient
import com.pouyaa.model.Meal
import javax.inject.Inject

class NetworkMealToMealListMapper @Inject constructor() {
    fun map(input: List<NetworkMeal>): List<Meal> {
        return input.map {
            Meal(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                imageUrl = it.imageUrl.orEmpty(),
                category = it.category.orEmpty(),
                nationality = it.nationality.orEmpty(),
                source = it.source.orEmpty(),
                instructions = it.instructions.orEmpty(),
                ingredients = mapIngredients(it)
            )
        }
    }

    private fun mapIngredients(meal: NetworkMeal): List<Ingredient> {
        return buildList {
            createIngredient(name = meal.ingredient1, portion = meal.measure1)?.let(::add)
            createIngredient(name = meal.ingredient2, portion = meal.measure2)?.let(::add)
            createIngredient(name = meal.ingredient3, portion = meal.measure3)?.let(::add)
            createIngredient(name = meal.ingredient4, portion = meal.measure4)?.let(::add)
            createIngredient(name = meal.ingredient5, portion = meal.measure5)?.let(::add)
            createIngredient(name = meal.ingredient6, portion = meal.measure6)?.let(::add)
            createIngredient(name = meal.ingredient7, portion = meal.measure7)?.let(::add)
            createIngredient(name = meal.ingredient8, portion = meal.measure8)?.let(::add)
            createIngredient(name = meal.ingredient9, portion = meal.measure9)?.let(::add)
            createIngredient(name = meal.ingredient10, portion = meal.measure10)?.let(::add)
            createIngredient(name = meal.ingredient11, portion = meal.measure11)?.let(::add)
            createIngredient(name = meal.ingredient12, portion = meal.measure12)?.let(::add)
            createIngredient(name = meal.ingredient13, portion = meal.measure13)?.let(::add)
            createIngredient(name = meal.ingredient14, portion = meal.measure14)?.let(::add)
            createIngredient(name = meal.ingredient15, portion = meal.measure15)?.let(::add)
            createIngredient(name = meal.ingredient16, portion = meal.measure16)?.let(::add)
            createIngredient(name = meal.ingredient17, portion = meal.measure17)?.let(::add)
            createIngredient(name = meal.ingredient18, portion = meal.measure18)?.let(::add)
            createIngredient(name = meal.ingredient19, portion = meal.measure19)?.let(::add)
            createIngredient(name = meal.ingredient20, portion = meal.measure20)?.let(::add)
        }
    }

    private fun createIngredient(name: String?, portion: String?): Ingredient? {
        if (name.isNullOrBlank()) return null
        return Ingredient(name = name, portion = portion.orEmpty())
    }
}