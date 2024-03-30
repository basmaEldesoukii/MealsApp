package com.basma.meals_list.data.remote

import com.basma.meals_list.domain.entity.MealsListDataModel
import javax.inject.Inject

class MealsListRemoteDataSourceImp @Inject constructor(
    private val remoteServices: MealsListRemoteServices
): MealsListRemoteDataSourceContract {
    override suspend fun getMealsList(categoryType: String): MealsListDataModel {
        return remoteServices.getMealsList(categoryType)
    }
}