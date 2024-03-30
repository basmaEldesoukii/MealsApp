package com.basma.homepage.presentation.viewmodel

import com.basma.base.UiIntent
import com.basma.base.UiState
import com.basma.homepage.domain.entity.Category
import com.basma.homepage.domain.entity.Data

class HomePageContract {
    sealed class HomePageIntent : UiIntent {
        data object OnFetchHomePageData : HomePageIntent()
        data class OnCategoryClicked(val category: Category) : HomePageIntent()
    }

    data class HomePageState(
        val homePageDataState: HomePageDataState,
        val selectedCategory: Category? = null
    ) : UiState

    sealed class HomePageDataState {
        data object Loading : HomePageDataState()
        data class Success(
            val data: Data
        ) : HomePageDataState()

        data class Error(val errorMsg: String?) : HomePageDataState()
    }
}