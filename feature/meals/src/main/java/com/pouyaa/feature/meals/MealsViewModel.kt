package com.pouyaa.feature.meals

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.pouyaa.common.result.Result
import com.pouyaa.domain.usecase.GetMealsByCategoryUseCase
import com.pouyaa.feature.meals.navigation.Meals
import com.pouyaa.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    init {
        fetchMealsByCategory()
    }

    private fun fetchMealsByCategory() {
        val categoryName = savedStateHandle.toRoute<Meals>().category
        viewModelScope.launch {
            uiState = UiState.Loading
            getMealsByCategoryUseCase.fetch(categoryName).collectLatest(::handleMealsResult)
        }
    }

    private fun handleMealsResult(result: Result<List<Meal>>) {
        uiState = when (result) {
            is Result.Success -> UiState.Success(data = result.data)
            is Result.Error -> UiState.Failed(message = result.throwable?.message.orEmpty())
        }
    }

    fun onRetryClicked() {
        fetchMealsByCategory()
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Success(val data: List<Meal>) : UiState
        data class Failed(val message: String) : UiState
    }
}