package com.pouyaa.feature.mealinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pouyaa.feature.mealinfo.MealInfoRoute
import kotlinx.serialization.Serializable

@Serializable
data class MealInfo(val id: String)

fun NavController.navigateToMealInfoScreen(id: String) {
    navigate(MealInfo(id = id))
}

fun NavGraphBuilder.mealInfoScreen() {
    composable<MealInfo> {
        MealInfoRoute()
    }
}