package com.basma.meal_details.data.local

interface MealDetailsLocalDataSourceContract {
    suspend fun getMealDetailsFromDataBase(): MealDetailsLocalEntity
    suspend fun insertMealDetails(meal: MealDetailsLocalEntity): Long
    suspend fun clearMealDetailsCashed(): Int
}