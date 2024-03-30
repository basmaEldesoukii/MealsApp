package com.basma.meals_list.presentation.viewmodel

import com.basma.base.UiIntent
import com.basma.base.UiState
import com.basma.meals_list.domain.entity.Meal

class MealsListContract {
    sealed class MealsListIntent : UiIntent {
        data object OnFetchMealsListData : MealsListIntent()
        data class OnMealClicked(val meal: Meal) : MealsListIntent()
    }

    data class MealsListState(
        val mealsListState: MealsListDataState,
        val selectedMeal: Meal? = null
    ) : UiState

    sealed class MealsListDataState {
        data object Loading : MealsListDataState()
        data class Success(
            val mealsList: List<Meal>
        ) : MealsListDataState()

        data class Error(val errorMsg: String?) : MealsListDataState()
    }
}