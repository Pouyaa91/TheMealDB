package com.pouyaa.core.network

import com.pouyaa.core.network.service.CategoriesApiService
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import kotlin.test.assertEquals


class CategoriesApiServiceTest {

    private lateinit var apiService: CategoriesApiService
    private lateinit var mockWebServer: MockWebServer

    private val moshi: Moshi =
        Moshi.Builder().add(Wrapped.ADAPTER_FACTORY).add(KotlinJsonAdapterFactory()).build()

    @Before
    fun setup() {
        mockWebServer = MockWebServer().apply(MockWebServer::start)
        apiService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(CategoriesApiService::class.java)
    }

    @Test
    fun checkCategoriesConvertsCorrectly() = runTest {
        val mockResponse = File("src/test/resources/categories.json").readText()

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))
        val result = apiService.getCategories()

        assertEquals(result.size, 2)
        assertEquals(result.getOrNull(0)?.id, "1")
        assertEquals(result.getOrNull(0)?.name, "first category")
        assertEquals(result.getOrNull(0)?.imageUrl, "first thumb")
        assertEquals(result.getOrNull(0)?.description, "first description")
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
