package com.basma.homepage.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.basma.base.BaseViewModel
import com.basma.common.utils.Resource
import com.basma.homepage.domain.entity.Category
import com.basma.homepage.domain.usecase.GetHomePageDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getHomePageDataUseCase: GetHomePageDataUseCase
) : BaseViewModel<HomePageContract.HomePageIntent, HomePageContract.HomePageState>() {

    override fun createInitialState(): HomePageContract.HomePageState {
        return HomePageContract.HomePageState(HomePageContract.HomePageDataState.Loading)
    }

    override fun handleIntent(intent: HomePageContract.HomePageIntent) {
        when (intent) {
            is HomePageContract.HomePageIntent.OnFetchHomePageData -> {
                fetchHomePageData()
            }

            is HomePageContract.HomePageIntent.OnCategoryClicked -> {
                val item = intent.category
                setSelectedCategory(item)
            }
        }
    }

    private fun fetchHomePageData() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomePageDataUseCase.invoke()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(homePageDataState = HomePageContract.HomePageDataState.Loading) }
                        }

                        is Resource.Success -> {
                            setState {
                                copy(
                                    homePageDataState = HomePageContract.HomePageDataState.Success(
                                        it.data.Data
                                    )
                                )
                            }
                        }

                        is Resource.Error -> {
                            setState {
                                copy(
                                    homePageDataState = HomePageContract.HomePageDataState.Error(
                                        it.exception.localizedMessage
                                    )
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun setSelectedCategory(category: Category){
        setState { copy(selectedCategory = category) }
    }
}
