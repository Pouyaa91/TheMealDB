package com.pouyaa.data.mapper

import com.pouyaa.core.network.model.meal.NetworkMeal
import meals.mapper.NetworkMealToMealListMapper
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class NetworkMealToMealListMapperTest {

    private val mapper = NetworkMealToMealListMapper()

    @Test
    fun checkMapperWorksCorrectly() {
        val networkResult = createRandomNetworkMealList()
        val mappedData = mapper.map(networkResult)

        assertEquals(networkResult.size, mappedData.size)
        networkResult.forEachIndexed { index, networkMeal ->
            assertEquals(mappedData.getOrNull(index)?.id, networkMeal.id)
            assertEquals(mappedData.getOrNull(index)?.name, networkMeal.name)
            assertEquals(mappedData.getOrNull(index)?.imageUrl, networkMeal.imageUrl)
            assertEquals(mappedData.getOrNull(index)?.instructions, networkMeal.instructions)
            assertEquals(mappedData.getOrNull(index)?.nationality, networkMeal.nationality)
            assertEquals(mappedData.getOrNull(index)?.category, networkMeal.category)
            assertEquals(mappedData.getOrNull(index)?.source, networkMeal.source)

            mappedData.getOrNull(index)?.ingredients?.getOrNull(0)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient1)
                assertEquals(ingredient.portion, networkMeal.measure1)
            }

            mappedData.getOrNull(index)?.ingredients?.getOrNull(1)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient2)
                assertEquals(ingredient.portion, networkMeal.measure2)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(2)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient3)
                assertEquals(ingredient.portion, networkMeal.measure3)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(3)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient4)
                assertEquals(ingredient.portion, networkMeal.measure4)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(4)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient5)
                assertEquals(ingredient.portion, networkMeal.measure5)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(5)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient6)
                assertEquals(ingredient.portion, networkMeal.measure6)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(6)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient7)
                assertEquals(ingredient.portion, networkMeal.measure7)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(7)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient8)
                assertEquals(ingredient.portion, networkMeal.measure8)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(8)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient9)
                assertEquals(ingredient.portion, networkMeal.measure9)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(9)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient10)
                assertEquals(ingredient.portion, networkMeal.measure10)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(10)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient11)
                assertEquals(ingredient.portion, networkMeal.measure11)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(11)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient12)
                assertEquals(ingredient.portion, networkMeal.measure12)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(12)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient13)
                assertEquals(ingredient.portion, networkMeal.measure13)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(13)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient14)
                assertEquals(ingredient.portion, networkMeal.measure14)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(14)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient15)
                assertEquals(ingredient.portion, networkMeal.measure15)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(15)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient16)
                assertEquals(ingredient.portion, networkMeal.measure16)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(16)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient17)
                assertEquals(ingredient.portion, networkMeal.measure17)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(17)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient18)
                assertEquals(ingredient.portion, networkMeal.measure18)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(18)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient19)
                assertEquals(ingredient.portion, networkMeal.measure19)
            }


            mappedData.getOrNull(index)?.ingredients?.getOrNull(19)?.let { ingredient ->
                assertEquals(ingredient.name, networkMeal.ingredient20)
                assertEquals(ingredient.portion, networkMeal.measure20)
            }

        }
    }

    private fun createRandomNetworkMealList(): List<NetworkMeal> {
        return buildList {
            (1..3).forEach {
                add(
                    NetworkMeal(
                        id = it.toString(),
                        name = Random.nextInt().toString(),
                        imageUrl = Random.nextInt().toString(),
                        instructions = Random.nextInt().toString(),
                        nationality = Random.nextInt().toString(),
                        category = Random.nextInt().toString(),
                        source = Random.nextInt().toString(),
                        ingredient1 = Random.nextInt().toString(),
                        ingredient2 = Random.nextInt().toString(),
                        ingredient3 = Random.nextInt().toString(),
                        ingredient4 = Random.nextInt().toString(),
                        ingredient5 = Random.nextInt().toString(),
                        ingredient6 = Random.nextInt().toString(),
                        ingredient7 = Random.nextInt().toString(),
                        ingredient8 = Random.nextInt().toString(),
                        ingredient9 = Random.nextInt().toString(),
                        ingredient10 = Random.nextInt().toString(),
                        ingredient11 = Random.nextInt().toString(),
                        ingredient12 = Random.nextInt().toString(),
                        ingredient13 = Random.nextInt().toString(),
                        ingredient14 = Random.nextInt().toString(),
                        ingredient15 = Random.nextInt().toString(),
                        ingredient16 = Random.nextInt().toString(),
                        ingredient17 = Random.nextInt().toString(),
                        ingredient18 = Random.nextInt().toString(),
                        ingredient19 = Random.nextInt().toString(),
                        ingredient20 = Random.nextInt().toString(),
                        measure1 = Random.nextInt().toString(),
                        measure2 = Random.nextInt().toString(),
                        measure3 = Random.nextInt().toString(),
                        measure4 = Random.nextInt().toString(),
                        measure5 = Random.nextInt().toString(),
                        measure6 = Random.nextInt().toString(),
                        measure7 = Random.nextInt().toString(),
                        measure8 = Random.nextInt().toString(),
                        measure9 = Random.nextInt().toString(),
                        measure10 = Random.nextInt().toString(),
                        measure11 = Random.nextInt().toString(),
                        measure12 = Random.nextInt().toString(),
                        measure13 = Random.nextInt().toString(),
                        measure14 = Random.nextInt().toString(),
                        measure15 = Random.nextInt().toString(),
                        measure16 = Random.nextInt().toString(),
                        measure17 = Random.nextInt().toString(),
                        measure18 = Random.nextInt().toString(),
                        measure19 = Random.nextInt().toString(),
                        measure20 = Random.nextInt().toString()
                    )
                )
            }
        }
    }
}