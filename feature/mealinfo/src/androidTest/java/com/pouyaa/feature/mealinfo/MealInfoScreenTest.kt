package com.pouyaa.feature.mealinfo

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.pouyaa.model.Ingredient
import com.pouyaa.model.Meal
import org.junit.Rule
import org.junit.Test

class MealInfoScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val meal = Meal(
        id = "0",
        name = "sample name",
        imageUrl = "url",
        source = "sample source",
        nationality = "sample nationality",
        category = "sample category",
        instructions = "sample instruction",
        ingredients = listOf(Ingredient(name = "sample ingredient", "sample portion"))
    )


    @Test
    fun checkCircularIndicatorOnLoadingState() {
        composeTestRule.setContent {
            MealInfoScreen(
                state = MealInfoViewModel.UiState.Loading,
                onRetryClicked = {})
        }
        composeTestRule.onNodeWithContentDescription(
            "Loading"
        ).assertExists()
    }

    @Test
    fun checkCategoryListOnSuccessState() {

        composeTestRule.setContent {
            MealInfoScreen(
                state = MealInfoViewModel.UiState.Success(meal = meal),
                onRetryClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription("sample name").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.ingredients)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.how_to_prepare)
        ).assertExists()
    }

    @Test
    fun checkExpandableSectionWorksCorrectly() {

        composeTestRule.setContent {
            MealInfoScreen(
                state = MealInfoViewModel.UiState.Success(meal = meal),
                onRetryClicked = {}
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.how_to_prepare)
        ).performClick()

        composeTestRule.onNodeWithText(
            text = "instruction",
            substring = true
        ).assertExists()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.how_to_prepare)
        ).performClick()

        composeTestRule.onNodeWithText(
            text = "instruction",
            substring = true
        ).assertDoesNotExist()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.ingredients)
        ).performClick()

        composeTestRule.onNodeWithText(
            text = "portion",
            substring = true
        ).assertExists()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getString(R.string.ingredients)
        ).performClick()

        composeTestRule.onNodeWithText(
            text = "portion",
            substring = true
        ).assertDoesNotExist()

    }

    @Test
    fun checkErrorViewOnErrorState() {

        composeTestRule.setContent {
            MealInfoScreen(
                state = MealInfoViewModel.UiState.Failed(message = "test error"),
                onRetryClicked = {}
            )
        }
        composeTestRule.onNodeWithText("test error").assertExists()
        composeTestRule.onNodeWithText("Retry").assertExists()

    }
}