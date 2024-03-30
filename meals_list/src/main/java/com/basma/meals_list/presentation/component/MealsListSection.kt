package com.basma.meals_list.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MealsListSection() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        //TODO add mealsList
        /*items(meals) { meal ->
            MealsListItem()
        }*/
    }
}