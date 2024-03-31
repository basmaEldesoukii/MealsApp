package com.basma.meal_details.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.basma.base.BaseViewModel
import com.basma.common.utils.Resource
import com.basma.meal_details.domain.usecase.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetailsUseCase
) : BaseViewModel<MealDetailsContract.MealDetailsIntent, MealDetailsContract.MealDetailsState>() {

    override fun createInitialState(): MealDetailsContract.MealDetailsState {
        return MealDetailsContract.MealDetailsState(MealDetailsContract.MealDetailsDataState.Loading)
    }

    override fun handleIntent(intent: MealDetailsContract.MealDetailsIntent) {
        when (intent) {
            is MealDetailsContract.MealDetailsIntent.OnFetchMealDetails -> {
                fetchMealDetails(intent.mealID)
            }
        }
    }

    private fun fetchMealDetails(mealID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMealDetailsUseCase.invoke(mealID)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(mealDetailsState = MealDetailsContract.MealDetailsDataState.Loading) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    mealDetailsState = MealDetailsContract.MealDetailsDataState.Success(
                                        it.data
                                    )
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState {
                                copy(
                                    mealDetailsState = MealDetailsContract.MealDetailsDataState.Error(
                                        it.exception.localizedMessage
                                    )
                                )
                            }
                        }
                    }
                }
        }
    }
}