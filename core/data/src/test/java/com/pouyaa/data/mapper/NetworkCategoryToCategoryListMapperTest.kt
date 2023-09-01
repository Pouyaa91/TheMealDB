package com.pouyaa.data.mapper

import com.pouyaa.core.network.model.NetworkCategory
import com.pouyaa.data.categories.mapper.NetworkCategoryToCategoryListMapper
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class NetworkCategoryToCategoryListMapperTest {

    private val mapper = NetworkCategoryToCategoryListMapper()

    @Test
    fun checkMapperWorksCorrectly() {
        val networkResult = createRandomNetworkCategoryList()
        val mappedData = mapper.map(networkResult)

        assertEquals(networkResult.size, mappedData.size)
        networkResult.forEachIndexed { index, networkCategory ->
            assertEquals(mappedData.getOrNull(index)?.id, networkCategory.id)
            assertEquals(mappedData.getOrNull(index)?.name, networkCategory.name)
            assertEquals(mappedData.getOrNull(index)?.imageUrl, networkCategory.imageUrl)
            assertEquals(mappedData.getOrNull(index)?.description, networkCategory.description)
        }
    }

    private fun createRandomNetworkCategoryList(): List<NetworkCategory> {
        return buildList {
            (1..3).forEach {
                add(
                    NetworkCategory(
                        id = it.toString(),
                        name = Random.nextInt().toString(),
                        imageUrl = Random.nextInt().toString(),
                        description = Random.nextInt().toString()
                    )
                )
            }
        }
    }
}