package com.basma.meal_details.domain.contract

import com.basma.common.utils.Resource
import com.basma.meal_details.domain.entity.Meal
import kotlinx.coroutines.flow.Flow

interface MealDetailsRepositoryContract {
    suspend fun getMealDetails(): Flow<Resource<List<Meal>>>
}