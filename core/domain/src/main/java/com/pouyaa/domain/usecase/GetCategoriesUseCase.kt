package com.pouyaa.domain.usecase

import com.pouyaa.data.categories.repository.CategoriesRepository
import com.pouyaa.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {
    fun fetch(): Flow<List<Category>> = categoriesRepository.fetch()
}