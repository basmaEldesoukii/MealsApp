package com.basma.homepage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basma.homepage.data.local.entity.IngredientLocalEntity

@Dao
interface IngredientDao {
    @Query("select * from IngredientTable")
    suspend fun getAllIngredientsList(): List<IngredientLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientsList(ingredients: List<IngredientLocalEntity>): List<Long>

    @Query("delete from IngredientTable")
    suspend fun clearIngredientsListCash():Int
}