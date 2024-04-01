package com.basma.meal_details.data.remote

import com.basma.meal_details.domain.entity.MealDetailsDataModel
import com.basma.meals_list.domain.entity.MealsListDataModel

interface MealDetailsRemoteDataSourceContract {
    suspend fun getMealDetails(mealId: String): MealDetailsDataModel
}