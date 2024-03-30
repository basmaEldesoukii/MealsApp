package com.basma.meals_list.data.remote

import com.basma.meals_list.domain.entity.MealsListDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealsListRemoteServices {
    @GET("filter.php")
    suspend fun getMealsList( @Query("c") categoryType: String
    ): MealsListDataModel
}