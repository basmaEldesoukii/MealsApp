package com.basma.meal_details.presentation.screen

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
import com.basma.meal_details.presentation.component.MealDetailsSection
import com.basma.meal_details.presentation.viewmodel.MealDetailsContract
import com.basma.meal_details.presentation.viewmodel.MealDetailsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailsScreen(
    navController: NavController,
    viewModel: MealDetailsViewModel,
    mealId: String
) {
    viewModel.setIntent(MealDetailsContract.MealDetailsIntent.OnFetchMealDetails(mealId))
    val currentState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meal Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to Meals List")
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (val state = currentState.mealDetailsState) {
                is MealDetailsContract.MealDetailsDataState.Loading -> {
                    ProgressComponent()
                }

                is MealDetailsContract.MealDetailsDataState.Success -> {
                    val meal = state.meal
                    Spacer(modifier = Modifier.height(52.dp))
                    MealDetailsSection(meal = meal)
                }

                is MealDetailsContract.MealDetailsDataState.Error -> {
                    ErrorComponent(message = state.errorMsg.toString())
                }
            }
        }
    }
}