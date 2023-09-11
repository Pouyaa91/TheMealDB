package com.pouyaa.domain

import com.pouyaa.common.result.Result
import com.pouyaa.data.categories.repository.CategoriesRepository
import com.pouyaa.domain.usecase.GetCategoriesUseCase
import com.pouyaa.model.Category
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

class GetCategoriesUseCaseTest {

    @MockK
    lateinit var repository: CategoriesRepository

    private lateinit var useCase: GetCategoriesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetCategoriesUseCase(repository)
    }

    @Test
    fun checkUseCaseReturnsCorrectlyOnSuccessResult() = runTest {
        val mockCategories = listOf(
            Category(
                id = "test id",
                name = "test name",
                imageUrl = "test url",
                description = "test description"
            )
        )

        coEvery { repository.fetch() } returns flowOf(Result.Success(data = mockCategories))

        useCase.fetch().collectLatest { result ->
            val categories = (result as? Result.Success)?.data
            assertEquals(categories, mockCategories)
        }

        coVerifySequence {
            repository.fetch()
        }
    }

    @Test
    fun checkUseCaseReturnCorrectlyOnErrorResult() = runTest {

        coEvery { repository.fetch() } returns flowOf(Result.Error(throwable = Exception("test error")))

        useCase.fetch().collectLatest { result ->
            val message = (result as? Result.Error)?.throwable?.message
            assertEquals(message, "test error")
        }

        coVerifySequence {
            repository.fetch()
        }
    }
}