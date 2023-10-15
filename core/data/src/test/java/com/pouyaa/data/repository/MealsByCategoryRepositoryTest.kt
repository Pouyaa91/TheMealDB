package com.pouyaa.data.repository

import com.pouyaa.common.result.Result
import com.pouyaa.core.network.model.meal.NetworkMealsWrapper
import com.pouyaa.core.network.service.MealsApiService
import com.pouyaa.model.Ingredient
import com.pouyaa.model.Meal
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import meals.mapper.NetworkMealToMealListMapper
import meals.repository.MealsByCategoryRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealsByCategoryRepositoryTest {

    @MockK
    lateinit var mapper: NetworkMealToMealListMapper

    @MockK
    lateinit var apiService: MealsApiService

    private lateinit var repository: MealsByCategoryRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = MealsByCategoryRepositoryImpl(apiService, mapper)
    }

    @Test
    fun checkRepositoryReturnsCorrectly() = runTest {
        val mappedResult =
            listOf(
                Meal(
                    id = "1",
                    name = "test name",
                    imageUrl = "test url",
                    instructions = "test instructions",
                    category = "test category",
                    nationality = "test nationality",
                    source = "test source",
                    ingredients = listOf(Ingredient(name = "test name", "test portion"))
                )
            )

        coEvery { apiService.getMealsByCategory(any()) } returns NetworkMealsWrapper(mealsList = emptyList())
        coEvery { mapper.map(any()) } returns mappedResult

        repository.fetch("").collectLatest { result ->
            val categories = (result as? Result.Success)?.data
            assertEquals(categories?.size, 1)
            assertEquals(categories?.first(), mappedResult.first())
        }

        coVerifySequence {
            apiService.getMealsByCategory(any())
            mapper.map(any())
        }
    }


    @Test
    fun checkRepositoryReturnsCorrectlyOnException() = runTest {
        coEvery { apiService.getMealsByCategory(any()) } throws Exception("test error")

        repository.fetch("").collectLatest {
            val message = (it as? Result.Error)?.throwable?.message
            assertEquals(message, "test error")
        }

        coVerifySequence {
            apiService.getMealsByCategory(any())
        }
    }

}