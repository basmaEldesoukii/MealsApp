package com.basma.meal_details.data.remote

import com.basma.meal_details.domain.entity.MealDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MealDetailsRemoteServices {
    @GET("lookup.php?i={meal_id}")
    suspend fun getMealDetails(
        @Path("meal_id") mealId: Int
    ): MealDetailsDataModel
}