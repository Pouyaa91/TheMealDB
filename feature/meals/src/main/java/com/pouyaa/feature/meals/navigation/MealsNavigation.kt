package com.pouyaa.feature.meals.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pouyaa.feature.meals.MealsRoute
import com.pouyaa.feature.meals.MealsViewModel

private const val MEALS_ROUTE = "meals_route"
private const val MEALS_ARGS = "/{${MealsViewModel.MEALS_SCREEN_ARG}}"
private const val MEALS_NAVIGATION_ROUTE = MEALS_ROUTE + MEALS_ARGS

fun NavController.navigateToMealsScreen(categoryName: String) {
    navigate("$MEALS_ROUTE/$categoryName")
}

fun NavGraphBuilder.mealsScreen(onMealClicked: (String) -> Unit) {
    composable(
        route = MEALS_NAVIGATION_ROUTE,
        arguments = listOf(
            navArgument(MealsViewModel.MEALS_SCREEN_ARG) { type = NavType.StringType }
        )
    ) {
        MealsRoute(onMealClicked = onMealClicked)
    }
}