package com.basma.meals_list.data.local

class MealsListLocalDataSourceImp(
    private val mealsListDao: MealsListDao
) : MealsListLocalDataSourceContract {
    override suspend fun getMealsListFromDataBase(): List<MealLocalEntity> {
        return mealsListDao.getMealsList()
    }

    override suspend fun insertMealsList(mealsList: List<MealLocalEntity>): List<Long> {
        return mealsListDao.insertMealsList(mealsList)
    }

    override suspend fun clearMealsListCashed(): Int {
        return mealsListDao.clearMealsListCash()
    }
}