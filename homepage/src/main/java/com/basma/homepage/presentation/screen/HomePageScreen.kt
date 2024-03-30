package com.basma.homepage.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.basma.homepage.presentation.component.CategoriesSection
import com.basma.homepage.presentation.component.HighlightsSection
import com.basma.homepage.presentation.component.IngredientsSection
import com.basma.homepage.presentation.component.MealBanner
import com.basma.homepage.presentation.component.MealsSection
import com.basma.homepage.presentation.component.TitleHeader
import com.basma.homepage.presentation.viewmodel.HomePageViewModel

@Composable
fun HomePageScreen(
    navController: NavController,
    viewModel: HomePageViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            MealBanner()
        }
        item {
            TitleHeader(title = "")
            CategoriesSection()
        }
        item {
            TitleHeader(title = "")
            MealsSection()
        }
        item {
            TitleHeader(title = "")
            IngredientsSection()
        }
        item {
            TitleHeader(title = "")
            HighlightsSection()
        }
    }
}