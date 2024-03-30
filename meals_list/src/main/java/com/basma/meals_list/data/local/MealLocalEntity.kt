package com.basma.meals_list.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealsListTable")
data class MealLocalEntity(
    @PrimaryKey
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)