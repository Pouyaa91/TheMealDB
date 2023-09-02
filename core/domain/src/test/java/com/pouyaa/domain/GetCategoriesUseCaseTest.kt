package com.pouyaa.domain

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
    fun checkUseCaseReturnsCorrectly() = runTest {
        val mockCategories = listOf(
            Category(
                id = "test id",
                name = "test name",
                imageUrl = "test url",
                description = "test description"
            )
        )

        coEvery { repository.fetch() } returns flowOf(mockCategories)

        useCase.fetch().collectLatest { categories ->
            assertEquals(categories, mockCategories)
        }

        coVerifySequence {
            repository.fetch()
        }
    }
}