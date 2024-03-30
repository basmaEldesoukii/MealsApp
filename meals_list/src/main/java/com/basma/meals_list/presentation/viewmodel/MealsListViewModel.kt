package com.basma.meals_list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.basma.base.BaseViewModel
import com.basma.common.utils.Resource
import com.basma.meals_list.domain.entity.Meal
import com.basma.meals_list.domain.usecase.GetMealsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsListViewModel @Inject constructor(
    private val getMealsListUseCase: GetMealsListUseCase
) : BaseViewModel<MealsListContract.MealsListIntent, MealsListContract.MealsListState>() {

    override fun createInitialState(): MealsListContract.MealsListState {
        return MealsListContract.MealsListState(MealsListContract.MealsListDataState.Loading)
    }

    override fun handleIntent(intent: MealsListContract.MealsListIntent) {
        when (intent) {
            is MealsListContract.MealsListIntent.OnFetchMealsListData -> {
                fetchMealsList(intent.categoryType)
            }

            is MealsListContract.MealsListIntent.OnMealClicked -> {
                val item = intent.meal
                setSelectedMeal(item)
            }
        }
    }

    private fun fetchMealsList(categoryType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getMealsListUseCase.invoke(categoryType)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(mealsListState = MealsListContract.MealsListDataState.Loading) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    mealsListState = MealsListContract.MealsListDataState.Success(
                                        it.data
                                    )
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState {
                                copy(
                                    mealsListState = MealsListContract.MealsListDataState.Error(
                                        it.exception.localizedMessage
                                    )
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun setSelectedMeal(meal: Meal) {
        setState { copy(selectedMeal = meal) }
    }
}