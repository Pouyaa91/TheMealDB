package com.pouyaa.feature.categories

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.pouyaa.model.Category
import org.junit.Rule
import org.junit.Test

class CategoriesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkCircularIndicatorOnLoadingState() {
        composeTestRule.setContent {
            CategoriesScreen(state = CategoriesViewModel.UiState.Loading, onCategoryClicked = {})
        }
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.resources.getString(R.string.loading)
        ).assertExists()
    }

    @Test
    fun checkCategoryListOnSuccessState() {
        val categories = listOf(
            Category(
                id = "0",
                name = "sample name",
                imageUrl = "url",
                description = "description"
            )
        )
        composeTestRule.setContent {
            CategoriesScreen(
                state = CategoriesViewModel.UiState.Success(data = categories),
                onCategoryClicked = {})
        }
        composeTestRule.onNodeWithContentDescription(categories.first().name).assertExists()
    }
}