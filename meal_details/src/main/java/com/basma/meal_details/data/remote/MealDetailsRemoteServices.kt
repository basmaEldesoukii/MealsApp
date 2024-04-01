package com.basma.meal_details.data.remote

import com.basma.meal_details.domain.entity.MealDetailsDataModel
import com.basma.meals_list.domain.entity.MealsListDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDetailsRemoteServices {
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: String
    ): MealDetailsDataModel
}