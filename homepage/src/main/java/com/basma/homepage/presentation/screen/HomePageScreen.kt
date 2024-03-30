package com.basma.homepage.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.basma.common.ui.ErrorComponent
import com.basma.common.ui.ProgressComponent
import com.basma.homepage.presentation.component.HomePageContent
import com.basma.homepage.presentation.viewmodel.HomePageContract
import com.basma.homepage.presentation.viewmodel.HomePageViewModel

@Composable
fun HomePageScreen(
    navController: NavController,
    viewModel: HomePageViewModel
) {
    viewModel.setIntent(HomePageContract.HomePageIntent.OnFetchHomePageData)
    val currentState by viewModel.uiState.collectAsState()

    when (val state = currentState.homePageDataState) {
        is HomePageContract.HomePageDataState.Loading -> {
            ProgressComponent()
        }

        is HomePageContract.HomePageDataState.Success -> {
            val data = state.data
            HomePageContent(data = data, navController = navController)
        }

        is HomePageContract.HomePageDataState.Error -> {
            ErrorComponent(message = state.errorMsg.toString())
        }
    }
}