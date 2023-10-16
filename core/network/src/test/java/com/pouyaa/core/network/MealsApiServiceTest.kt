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
            .create(MealsApiService::class.java)
    }

    @Test
    fun checkMealsConvertsCorrectly() = runTest {
        val mockResponse = File("src/test/resources/meals.json").readText()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))
        val meals = apiService.getMealsByCategory("").mealsList

        assertEquals(meals.size, 2)
        assertEquals(meals.firstOrNull()?.id, "first meal id")
        assertEquals(meals.firstOrNull()?.name, "first meal")
        assertEquals(meals.firstOrNull()?.imageUrl, "first meal image")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
