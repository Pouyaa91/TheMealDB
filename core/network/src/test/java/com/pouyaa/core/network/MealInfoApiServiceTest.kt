package com.pouyaa.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pouyaa.core.network.service.MealInfoApiService
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.io.File
import kotlin.test.assertEquals


class MealInfoApiServiceTest {

    private lateinit var apiService: MealInfoApiService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply(MockWebServer::start)
        apiService = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MealInfoApiService::class.java)
    }

    @Test
    fun checkMealInfoConvertsCorrectly() = runTest {
        val mockResponse = File("src/test/resources/mealInfo.json").readText()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))
        val mealInfo = apiService.getMealInfo("").mealsList.firstOrNull()

        assertEquals(mealInfo?.id, "100")
        assertEquals(mealInfo?.name, "meal name")
        assertEquals(mealInfo?.category, "category")
        assertEquals(mealInfo?.nationality, "nationality")
        assertEquals(mealInfo?.ingredient1, "ingredient 1")
        assertEquals(mealInfo?.ingredient2, "ingredient 2")
        assertEquals(mealInfo?.ingredient3, "ingredient 3")
        assertEquals(mealInfo?.ingredient4, "ingredient 4")
        assertEquals(mealInfo?.ingredient5, "ingredient 5")
        assertEquals(mealInfo?.ingredient6, "ingredient 6")
        assertEquals(mealInfo?.ingredient7, "ingredient 7")
        assertEquals(mealInfo?.ingredient8, "ingredient 8")
        assertEquals(mealInfo?.ingredient9, "ingredient 9")
        assertEquals(mealInfo?.ingredient10, "ingredient 10")
        assertEquals(mealInfo?.ingredient11, "ingredient 11")
        assertEquals(mealInfo?.ingredient12, "ingredient 12")
        assertEquals(mealInfo?.ingredient13, "ingredient 13")
        assertEquals(mealInfo?.ingredient14, "ingredient 14")
        assertEquals(mealInfo?.ingredient15, "ingredient 15")
        assertEquals(mealInfo?.ingredient16, "ingredient 16")
        assertEquals(mealInfo?.ingredient17, "ingredient 17")
        assertEquals(mealInfo?.ingredient18, "ingredient 18")
        assertEquals(mealInfo?.ingredient19, "ingredient 19")
        assertEquals(mealInfo?.ingredient20, "ingredient 20")
        assertEquals(mealInfo?.measure1, "measure 1")
        assertEquals(mealInfo?.measure2, "measure 2")
        assertEquals(mealInfo?.measure3, "measure 3")
        assertEquals(mealInfo?.measure4, "measure 4")
        assertEquals(mealInfo?.measure5, "measure 5")
        assertEquals(mealInfo?.measure6, "measure 6")
        assertEquals(mealInfo?.measure7, "measure 7")
        assertEquals(mealInfo?.measure8, "measure 8")
        assertEquals(mealInfo?.measure9, "measure 9")
        assertEquals(mealInfo?.measure10, "measure 10")
        assertEquals(mealInfo?.measure11, "measure 11")
        assertEquals(mealInfo?.measure12, "measure 12")
        assertEquals(mealInfo?.measure13, "measure 13")
        assertEquals(mealInfo?.measure14, "measure 14")
        assertEquals(mealInfo?.measure15, "measure 15")
        assertEquals(mealInfo?.measure16, "measure 16")
        assertEquals(mealInfo?.measure17, "measure 17")
        assertEquals(mealInfo?.measure18, "measure 18")
        assertEquals(mealInfo?.measure19, "measure 19")
        assertEquals(mealInfo?.measure20, "measure 20")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
