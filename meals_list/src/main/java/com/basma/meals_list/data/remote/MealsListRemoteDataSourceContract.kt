package com.basma.meals_list.data.remote

import com.basma.meals_list.domain.entity.MealsListDataModel

interface MealsListRemoteDataSourceContract {
    suspend fun getMealsList(categoryType: String): MealsListDataModel
    
}