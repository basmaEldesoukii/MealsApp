package com.basma.meals_list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealsListDao {
    @Query("select * from MealsListTable")
    suspend fun getMealsList(): List<MealLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealsList(mealsList: List<MealLocalEntity>): List<Long>

    @Query("delete from MealsListTable")
    suspend fun clearMealsListCash(): Int
}