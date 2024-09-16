package com.pouyaa.themealdb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pouyaa.feature.categories.navigation.Categories
import com.pouyaa.feature.categories.navigation.categoriesScreen
import com.pouyaa.feature.mealinfo.navigation.mealInfoScreen
import com.pouyaa.feature.mealinfo.navigation.navigateToMealInfoScreen
import com.pouyaa.feature.meals.navigation.mealsScreen
import com.pouyaa.feature.meals.navigation.navigateToMealsScreen

@Composable
fun TheMealDbNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Categories,
        modifier = modifier
    ) {
        categoriesScreen(navController::navigateToMealsScreen)
        mealsScreen(navController::navigateToMealInfoScreen)
        mealInfoScreen()
    }
}