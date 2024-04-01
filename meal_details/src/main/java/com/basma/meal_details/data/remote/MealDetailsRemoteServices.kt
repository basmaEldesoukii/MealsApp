package com.basma.meal_details.data.remote

import com.basma.meal_details.domain.entity.MealDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDetailsRemoteServices {
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("s") mealId: String
    ): MealDetailsDataModel
}