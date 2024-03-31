package com.basma.meal_details.data.local

import javax.inject.Inject

class MealDetailsLocalDataSourceImp @Inject constructor(
    private val mealDetailsDao: MealDetailsDao
) : MealDetailsLocalDataSourceContract {
    override suspend fun getMealDetailsFromDataBase(mealId: String): MealDetailsLocalEntity {
        return mealDetailsDao.getMealDetails(mealId)
    }

    override suspend fun insertMealDetails(meal: MealDetailsLocalEntity): Long {
        return mealDetailsDao.insertMealDetails(meal)
    }

    override suspend fun clearMealDetailsCashed(): Int {
        return mealDetailsDao.clearMealDetailsCash()
    }
}