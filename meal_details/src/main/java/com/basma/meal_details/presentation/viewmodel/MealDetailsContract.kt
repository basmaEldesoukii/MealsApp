package com.basma.meal_details.presentation.viewmodel

import com.basma.base.UiIntent
import com.basma.base.UiState
import com.basma.meal_details.domain.entity.MealDetails

class MealDetailsContract {
    sealed class MealDetailsIntent : UiIntent {
        data class OnFetchMealDetails(val mealID: Int) : MealDetailsIntent()
    }

    data class MealDetailsState(
        val mealDetailsState: MealDetailsDataState,
    ) : UiState

    sealed class MealDetailsDataState {
        data object Loading : MealDetailsDataState()
        data class Success(
            val meal: MealDetails
        ) : MealDetailsDataState()
        data class Error(val errorMsg: String?) : MealDetailsDataState()
    }
}