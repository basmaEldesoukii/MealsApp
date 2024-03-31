package com.basma.meals_list.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.basma.meals_list.domain.entity.Meal

@Composable
fun MealsListSection(meals: List<Meal>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(meals) { meal ->
            MealsListItem(meal){
                navController.navigate(route = "meal_details/${meal.idMeal}")
            }
        }
    }
}