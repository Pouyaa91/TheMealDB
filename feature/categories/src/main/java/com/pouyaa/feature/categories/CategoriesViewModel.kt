package com.pouyaa.feature.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaa.common.result.Result
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
        private set

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            uiState = UiState.Loading
            getCategoriesUseCase.fetch().collectLatest(::handleCategoriesResult)
        }
    }

    private fun handleCategoriesResult(result: Result<List<Category>>) {
        uiState = when (result) {
            is Result.Success -> UiState.Success(data = result.data)
            is Result.Error -> UiState.Failed(message = result.throwable?.message.orEmpty())
        }
    }

    fun onRetryClicked() {
        fetchCategories()
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Success(val data: List<Category>) : UiState
        data class Failed(val message: String) : UiState
    }
}