package com.pouyaa.feature.meals.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pouyaa.feature.meals.MealsRoute
import kotlinx.serialization.Serializable

@Serializable
data class Meals(val category: String)

fun NavController.navigateToMealsScreen(categoryName: String) {
    navigate(Meals(category = categoryName))
}

fun NavGraphBuilder.mealsScreen(onMealClicked: (String) -> Unit) {
    composable<Meals> { backStackEntry ->
        backStackEntry.toRoute<Meals>().category
        MealsRoute(onMealClicked = onMealClicked)
    }
}