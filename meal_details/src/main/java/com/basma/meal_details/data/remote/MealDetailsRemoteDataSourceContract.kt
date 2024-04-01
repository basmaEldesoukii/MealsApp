package com.basma.meal_details.data.remote

import com.basma.meal_details.domain.entity.MealDetailsDataModel

interface MealDetailsRemoteDataSourceContract {
    suspend fun getMealDetails(mealId: String): MealDetailsDataModel
}