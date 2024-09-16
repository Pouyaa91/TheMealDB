package com.pouyaa.feature.categories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pouyaa.feature.categories.CategoriesRoute
import kotlinx.serialization.Serializable

@Serializable
object Categories

fun NavGraphBuilder.categoriesScreen(onCategoryClicked: (String) -> Unit) {
    composable<Categories> {
        CategoriesRoute(onCategoryClicked = onCategoryClicked)
    }
}