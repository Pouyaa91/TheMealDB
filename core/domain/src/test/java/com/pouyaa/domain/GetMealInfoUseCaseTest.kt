package com.pouyaa.domain

import com.pouyaa.common.result.Result
import com.pouyaa.data.mealinfo.repository.MealInfoRepository
import com.pouyaa.domain.usecase.GetMealInfoUseCase
import com.pouyaa.model.Ingredient
import com.pouyaa.model.Meal
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealInfoUseCaseTest {

    @MockK
    lateinit var repository: MealInfoRepository

    private lateinit var useCase: GetMealInfoUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetMealInfoUseCase(repository)
    }

    @Test
    fun checkUseCaseReturnsCorrectlyOnSuccessResult() = runTest {
        val mockMeal =
            Meal(
                id = "test id",
                name = "test name",
                imageUrl = "test url",
                instructions = "test instructions",
                category = "test category",
                nationality = "test nationality",
                source = "test source",
                ingredients = listOf(Ingredient(name = "test name", "test portion"))
            )

        coEvery { repository.fetch(any()) } returns flowOf(Result.Success(data = mockMeal))

        useCase.fetch("").collectLatest { result ->
            val meal = (result as? Result.Success)?.data
            assertEquals(meal, mockMeal)
        }

        coVerifySequence {
            repository.fetch(any())
        }
    }

    @Test
    fun checkUseCaseReturnCorrectlyOnErrorResult() = runTest {

        coEvery { repository.fetch(any()) } returns flowOf(Result.Error(throwable = Exception("test error")))

        useCase.fetch("").collectLatest { result ->
            val message = (result as? Result.Error)?.throwable?.message
            assertEquals(message, "test error")
        }

        coVerifySequence {
            repository.fetch(any())
        }
    }
}