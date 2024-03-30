package com.basma.meals_list.data.remote

import com.basma.meals_list.domain.entity.MealsListDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MealsListRemoteServices {
    @GET("filter.php?c={category_type}")
    suspend fun getMealsList(
        @Path("category_type") categoryType: String
    ): MealsListDataModel
}