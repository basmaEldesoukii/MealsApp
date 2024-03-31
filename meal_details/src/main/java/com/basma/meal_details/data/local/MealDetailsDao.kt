package com.basma.meal_details.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDetailsDao {
    @Query("select * from MealDetailsTable where idMeal=:mealId")
    suspend fun getMealDetails(mealId: String): MealDetailsLocalEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealDetails(meal: MealDetailsLocalEntity): Long

    @Query("delete from MealDetailsTable")
    suspend fun clearMealDetailsCash(): Int
}