package com.basma.meal_details.data.local

interface MealDetailsLocalDataSourceContract {
    suspend fun getMealDetailsByIDFromDataBase(mealId: Int): MealDetailsLocalEntity
    suspend fun insertMealDetails(meal: MealDetailsLocalEntity): Long
    suspend fun clearMealDetailsCashed(): Int
}