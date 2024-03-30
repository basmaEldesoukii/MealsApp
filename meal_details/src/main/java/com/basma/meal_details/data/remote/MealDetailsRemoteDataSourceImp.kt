package com.basma.meal_details.data.remote

import com.basma.meal_details.domain.entity.MealDetailsDataModel
import javax.inject.Inject

class MealDetailsRemoteDataSourceImp @Inject constructor(
    private val remoteServices: MealDetailsRemoteServices
):MealDetailsRemoteDataSourceContract {
    override suspend fun getMealDetails(mealId: Int): MealDetailsDataModel {
        return remoteServices.getMealDetails(mealId)
    }
}