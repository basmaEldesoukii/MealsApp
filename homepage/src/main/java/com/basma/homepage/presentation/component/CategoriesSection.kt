package com.basma.homepage.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.basma.homepage.domain.entity.Category

@Composable
fun CategoriesSection(categories: List<Category>, navController: NavController) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category = category) {
                navController.navigate(route = "meals_list/${category.strCategory}")
            }
        }
    }
}