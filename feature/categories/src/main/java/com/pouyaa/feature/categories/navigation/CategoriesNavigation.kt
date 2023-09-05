package com.pouyaa.feature.categories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pouyaa.feature.categories.CategoriesRoute

const val categoriesNavigationRoute = "categories_route"

fun NavGraphBuilder.categoriesScreen(onCategoryClicked: (String) -> Unit) {
    composable(
        route = categoriesNavigationRoute
    ) {
        CategoriesRoute(onCategoryClicked = onCategoryClicked)
    }
}