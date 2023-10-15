package com.pouyaa.mealinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pouyaa.common.result.Result
import com.pouyaa.domain.usecase.GetMealInfoUseCase
import com.pouyaa.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealInfoViewModel @Inject constructor(
    private val getMealInfoUseCase: GetMealInfoUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    init {
        fetchMealInfo()
    }

    private fun fetchMealInfo() {
        uiState = UiState.Loading
        val id = savedStateHandle.get<String>(MEAL_INFO_SCREEN_ARG).orEmpty()
        viewModelScope.launch {
            getMealInfoUseCase.fetch(id = id).collectLatest(::handleMealInfoResult)
        }
    }

    private fun handleMealInfoResult(result: Result<Meal>) {
        uiState = when (result) {
            is Result.Success -> UiState.Success(meal = result.data)
            is Result.Error -> UiState.Failed(message = result.throwable?.message.orEmpty())
        }
    }

    fun onRetryClicked() {
        fetchMealInfo()
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Success(val meal: Meal) : UiState
        data class Failed(val message: String) : UiState
    }

    companion object {
        const val MEAL_INFO_SCREEN_ARG = "meal_info_screen_arg"
    }
}