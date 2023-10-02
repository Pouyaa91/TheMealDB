package com.pouyaa.meals.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pouyaa.meals.MealsRoute
import com.pouyaa.meals.MealsViewModel

const val mealsNavigationRoute = "meals_route/{${MealsViewModel.MEALS_SCREEN_ARG}}"

fun NavController.navigateToMealsScreen(categoryName: String) {
    navigate("meals_route/$categoryName")
}

fun NavGraphBuilder.mealsScreen(onMealClicked: (String) -> Unit) {
    composable(
        route = mealsNavigationRoute,
        arguments = listOf(
            navArgument(MealsViewModel.MEALS_SCREEN_ARG) { type = NavType.StringType }
        )
    ) {
        MealsRoute(onMealClicked = onMealClicked)
    }
}