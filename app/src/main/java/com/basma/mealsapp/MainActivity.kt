package com.basma.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.basma.homepage.presentation.viewmodel.HomePageViewModel
import com.basma.meal_details.presentation.viewmodel.MealDetailsViewModel
import com.basma.meals_list.presentation.viewmodel.MealsListViewModel
import com.basma.mealsapp.ui.theme.MealsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homePageViewModel: HomePageViewModel by viewModels()
    private val mealsListViewModel: MealsListViewModel by viewModels()
    private val mealsDetailsViewModel: MealDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                AppNavigation(
                    homePageViewModel,
                    mealsListViewModel,
                    mealsDetailsViewModel
                )
            }
        }
    }
}