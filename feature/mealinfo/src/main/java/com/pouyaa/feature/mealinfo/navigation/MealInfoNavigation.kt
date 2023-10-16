package com.pouyaa.feature.mealinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pouyaa.feature.mealinfo.MealInfoRoute
import com.pouyaa.feature.mealinfo.MealInfoViewModel

private const val MEAL_INFO_ROUTE = "meal_info_route"
private const val MEAL_INFO_ARGS = "/{${MealInfoViewModel.MEAL_INFO_SCREEN_ARG}}"
private const val MEAL_INFO_NAVIGATION_ROUTE = MEAL_INFO_ROUTE + MEAL_INFO_ARGS

fun NavController.navigateToMealInfoScreen(id: String) {
    navigate("$MEAL_INFO_ROUTE/$id")
}

fun NavGraphBuilder.mealInfoScreen() {
    composable(
        route = MEAL_INFO_NAVIGATION_ROUTE,
        arguments = listOf(
            navArgument(MealInfoViewModel.MEAL_INFO_SCREEN_ARG) { type = NavType.StringType }
        )
    ) {
        MealInfoRoute()
    }
}