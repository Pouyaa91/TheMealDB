package com.pouyaa.data.repository

import com.pouyaa.common.result.Result
import com.pouyaa.core.network.model.category.NetworkCategoriesWrapper
import com.pouyaa.core.network.service.CategoriesApiService
import com.pouyaa.data.categories.mapper.NetworkCategoryToCategoryListMapper
import com.pouyaa.data.categories.repository.CategoriesRepositoryImpl
import com.pouyaa.model.Category
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CategoriesRepositoryTest {

    @MockK
    lateinit var mapper: NetworkCategoryToCategoryListMapper

    @MockK
    lateinit var apiService: CategoriesApiService

    private lateinit var repository: CategoriesRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = CategoriesRepositoryImpl(apiService, mapper)
    }

    @Test
    fun checkRepositoryReturnsCorrectly() = runTest {
        val mappedResult =
            listOf(
                Category(
                    id = "1",
                    name = "test name",
                    imageUrl = "test url",
                    description = "test description"
                )
            )

        coEvery { apiService.getCategories() } returns NetworkCategoriesWrapper(categories = emptyList())
        coEvery { mapper.map(any()) } returns mappedResult

        repository.fetch().collectLatest { result ->
            val categories = (result as? Result.Success)?.data
            assertEquals(categories?.size, 1)
            assertEquals(categories?.first(), mappedResult.first())
        }

        coVerifySequence {
            apiService.getCategories()
            mapper.map(any())
        }
    }


    @Test
    fun checkRepositoryReturnsCorrectlyOnException() = runTest {
        coEvery { apiService.getCategories() } throws Exception("test error")

        repository.fetch().collectLatest {
            val message = (it as? Result.Error)?.throwable?.message
            assertEquals(message, "test error")
        }

        coVerifySequence {
            apiService.getCategories()
        }
    }

}