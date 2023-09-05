package com.pouyaa.themealdb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pouyaa.feature.categories.navigation.categoriesScreen

@Composable
fun TheMealDbNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        categoriesScreen {
            //TODO implement clicking on categories
        }
    }
}