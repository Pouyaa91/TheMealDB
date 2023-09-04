package com.pouyaa.feature.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaa.domain.usecase.GetCategoriesUseCase
import com.pouyaa.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    var uiState by mutableStateOf<UiState>(UiState.Loading)

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.fetch().collectLatest { uiState = UiState.Success(data = it) }
        }
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Success(val data: List<Category>) : UiState
    }
}