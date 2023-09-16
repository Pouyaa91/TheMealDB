package com.pouyaa.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pouyaa.core.network.service.CategoriesApiService
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


class CategoriesApiServiceTest {

    private lateinit var apiService: CategoriesApiService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply(MockWebServer::start)
        apiService = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(CategoriesApiService::class.java)
    }

    @Test
    fun checkCategoriesConvertsCorrectly() = runTest {
        val mockResponse = File("src/test/resources/categories.json").readText()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))
        val categories = apiService.getCategories().categories

        assertEquals(categories.size, 2)
        assertEquals(categories.getOrNull(0)?.id, "1")
        assertEquals(categories.getOrNull(0)?.name, "first category")
        assertEquals(categories.getOrNull(0)?.imageUrl, "first thumb")
        assertEquals(categories.getOrNull(0)?.description, "first description")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
