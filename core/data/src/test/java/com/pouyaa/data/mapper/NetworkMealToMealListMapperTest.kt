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
        networkResult.forEachIndexed { index, networkCategory ->
            assertEquals(mappedData.getOrNull(index)?.id, networkCategory.id)
            assertEquals(mappedData.getOrNull(index)?.name, networkCategory.name)
            assertEquals(mappedData.getOrNull(index)?.imageUrl, networkCategory.imageUrl)
        }
    }

    private fun createRandomNetworkMealList(): List<NetworkMeal> {
        return buildList {
            (1..3).forEach {
                add(
                    NetworkMeal(
                        id = it.toString(),
                        name = Random.nextInt().toString(),
                        imageUrl = Random.nextInt().toString()
                    )
                )
            }
        }
    }
}