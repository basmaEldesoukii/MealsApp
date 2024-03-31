package com.basma.meals_list.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.basma.common.ui.ErrorComponent
import com.basma.common.ui.ProgressComponent
import com.basma.meals_list.presentation.component.CategoryBanner
import com.basma.meals_list.presentation.component.MealsListSection
import com.basma.meals_list.presentation.viewmodel.MealsListContract
import com.basma.meals_list.presentation.viewmodel.MealsListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsListScreen(
    navController: NavController,
    viewModel: MealsListViewModel,
    categoryType: String
) {
    viewModel.setIntent(MealsListContract.MealsListIntent.OnFetchMealsListData(categoryType))
    val currentState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top categories") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to homepage")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (val state = currentState.mealsListState) {
                is MealsListContract.MealsListDataState.Loading -> {
                    ProgressComponent()
                }

                is MealsListContract.MealsListDataState.Success -> {
                    val mealsList = state.mealsList
                    Spacer(modifier = Modifier.height(52.dp))
                    CategoryBanner(categoryType)
                    Spacer(modifier = Modifier.height(12.dp))
                    MealsListSection(meals = mealsList, navController = navController)
                }

                is MealsListContract.MealsListDataState.Error -> {
                    ErrorComponent(message = state.errorMsg.toString())
                }
            }
        }
    }
}