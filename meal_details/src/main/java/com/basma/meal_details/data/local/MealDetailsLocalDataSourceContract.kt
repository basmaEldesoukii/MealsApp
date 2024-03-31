package com.basma.meal_details.data.local

interface MealDetailsLocalDataSourceContract {
    suspend fun getMealDetailsFromDataBase(mealId: String): MealDetailsLocalEntity
    suspend fun insertMealDetails(meal: MealDetailsLocalEntity): Long
    suspend fun clearMealDetailsCashed(): Int
}