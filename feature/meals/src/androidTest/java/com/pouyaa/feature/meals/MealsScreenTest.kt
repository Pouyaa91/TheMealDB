package com.pouyaa.feature.meals

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.pouyaa.model.Ingredient
import com.pouyaa.model.Meal
import org.junit.Rule
import org.junit.Test

class MealsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun checkCircularIndicatorOnLoadingState() {
        composeTestRule.setContent {
            MealsScreen(
                state = MealsViewModel.UiState.Loading,
                onMealClicked = {},
                onRetryClicked = {})
        }
        composeTestRule.onNodeWithContentDescription("Loading").assertExists()
    }

    @Test
    fun checkMealListOnSuccessState() {
        val meals = listOf(
            Meal(
                id = "0",
                name = "sample name",
                imageUrl = "url",
                source = "sample source",
                nationality = "sample nationality",
                category = "sample category",
                instructions = "sample instruction",
                ingredients = listOf(Ingredient(name = "sample ingredient", "sample portion"))
            )
        )
        composeTestRule.setContent {
            MealsScreen(
                state = MealsViewModel.UiState.Success(data = meals),
                onMealClicked = {},
                onRetryClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(meals.first().name).assertExists()
    }

    @Test
    fun checkErrorViewOnErrorState() {

        composeTestRule.setContent {
            MealsScreen(
                state = MealsViewModel.UiState.Failed(message = "test error"),
                onMealClicked = {},
                onRetryClicked = {}
            )
        }
        composeTestRule.onNodeWithText("test error").assertExists()
        composeTestRule.onNodeWithText("Retry").assertExists()

    }
}