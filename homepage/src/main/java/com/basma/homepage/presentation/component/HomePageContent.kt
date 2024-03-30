package com.basma.homepage.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.basma.homepage.domain.entity.Data
import com.basma.homepage.domain.entity.HomePageDataType

@Composable
fun HomePageContent(data: Data, navController: NavController) {
    LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        item {
            data.DynamicCollectionViewModel.firstOrNull { it.Type == HomePageDataType.MOTDY }
                ?.let { dataItem ->
                    MealBanner(dataItem)
                }
        }
        data.DynamicCollectionViewModel.forEach { dataItem ->
            when (dataItem.Type) {
                HomePageDataType.Category -> {
                    item {
                        TitleHeader(title = dataItem.Title)
                        CategoriesSection(categories = dataItem.Categories)
                    }
                }
                HomePageDataType.Product -> {
                    item {
                        TitleHeader(title = dataItem.Title)
                        MealsSection(meals = dataItem.Meals)
                    }
                }
                HomePageDataType.Ingredients -> {
                    item {
                        TitleHeader(title = dataItem.Title)
                        IngredientsSection(ingredients = dataItem.Ingredients)
                    }
                }
                HomePageDataType.Announcement -> {
                    item {
                        TitleHeader(title = dataItem.Title)
                        HighlightsSection(highlights = dataItem.Announcements)
                    }
                }

                else -> {}
            }
        }
    }
}



