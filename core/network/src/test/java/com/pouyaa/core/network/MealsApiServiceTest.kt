package com.pouyaa.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pouyaa.core.network.service.MealsApiService
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


class MealsApiServiceTest {

    private lateinit var apiService: MealsApiService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply(MockWebServer::start)
        apiService = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MealsApiService::class.java)
    }

    @Test
    fun checkMealsConvertsCorrectly() = runTest {
        val mockResponse = File("src/test/resources/meals.json").readText()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))
        val categories = apiService.getMealsByCategory("").mealsList

        assertEquals(categories.size, 2)
        assertEquals(categories.getOrNull(0)?.id, "first meal id")
        assertEquals(categories.getOrNull(0)?.name, "first meal")
        assertEquals(categories.getOrNull(0)?.imageUrl, "first meal image")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
