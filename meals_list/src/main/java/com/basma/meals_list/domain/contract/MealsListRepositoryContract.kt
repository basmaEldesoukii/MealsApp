package com.basma.meals_list.domain.contract

import com.basma.common.utils.Resource
import com.basma.meals_list.domain.entity.Meal
import kotlinx.coroutines.flow.Flow

interface MealsListRepositoryContract {
    suspend fun getMealsList(categoryType: String): Flow<Resource<List<Meal>>>
}