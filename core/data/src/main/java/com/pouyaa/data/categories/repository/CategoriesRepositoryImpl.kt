package com.pouyaa.data.categories.repository

import com.pouyaa.core.network.service.CategoriesApiService
import com.pouyaa.data.categories.mapper.NetworkCategoryToCategoryListMapper
import com.pouyaa.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: CategoriesApiService,
    private val mapper: NetworkCategoryToCategoryListMapper
) : CategoriesRepository {
    override fun fetch(): Flow<List<Category>> {
        return flow {
            val result = apiService.getCategories()
            emit(mapper.map(result))
        }.catch {
            emit(emptyList())
        }
    }
}