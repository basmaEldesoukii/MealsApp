package com.basma.homepage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basma.homepage.data.local.entity.MealLocalEntity


@Dao
interface MealDao {
    @Query("select * from MealTable")
    suspend fun getAllMealsList(): List<MealLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealsList(meals: List<MealLocalEntity>): List<Long>

    @Query("delete from MealTable")
    suspend fun clearMealsListCash():Int
}