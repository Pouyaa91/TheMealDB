package com.pouyaa.data.categories.repository

import com.pouyaa.common.result.Result
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
    override fun fetch(): Flow<Result<List<Category>>> {
        return flow<Result<List<Category>>> {
            val result = apiService.getCategories()
            emit(Result.Success(data = mapper.map(result.categories)))
        }.catch {
            emit(Result.Error(it))
        }
    }
}