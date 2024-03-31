package com.basma.meal_details.domain.contract

import com.basma.common.utils.Resource
import com.basma.meal_details.domain.entity.MealDetails
import kotlinx.coroutines.flow.Flow

interface MealDetailsRepositoryContract {
    suspend fun getMealDetails(mealId: String): Flow<Resource<MealDetails>>
}