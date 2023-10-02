package com.pouyaa.domain

import com.pouyaa.common.result.Result
import com.pouyaa.domain.usecase.GetMealsByCategoryUseCase
import com.pouyaa.model.Meal
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import meals.repository.MealsByCategoryRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMealsByCategoryUseCaseTest {

    @MockK
    lateinit var repository: MealsByCategoryRepository

    private lateinit var useCase: GetMealsByCategoryUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetMealsByCategoryUseCase(repository)
    }

    @Test
    fun checkUseCaseReturnsCorrectlyOnSuccessResult() = runTest {
        val mockCategories = listOf(
            Meal(
                id = "test id",
                name = "test name",
                imageUrl = "test url"
            )
        )

        coEvery { repository.fetch(any()) } returns flowOf(Result.Success(data = mockCategories))

        useCase.fetch("").collectLatest { result ->
            val categories = (result as? Result.Success)?.data
            assertEquals(categories, mockCategories)
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