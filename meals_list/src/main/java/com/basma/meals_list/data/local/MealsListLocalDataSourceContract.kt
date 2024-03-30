package com.basma.meals_list.data.local

interface MealsListLocalDataSourceContract {
    suspend fun getMealsListFromDataBase(categoryType: String): List<MealLocalEntity>
    suspend fun insertMealsList(mealsList: List<MealLocalEntity>): List<Long>
    suspend fun clearMealsListCashed(): Int
}