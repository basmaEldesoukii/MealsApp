package com.basma.homepage.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.basma.homepage.domain.entity.Category

@Composable
fun CategoryItem(category: Category, onCategoryClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(95.dp)
            .height(125.dp)
            .clickable { onCategoryClick() },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column {
            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = category.strCategory,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
            )
            Text(
                text = category.strCategory,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp)
                    .weight(0.2f)
            )
        }
    }
}