package com.basma.homepage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basma.homepage.data.local.entity.CategoryLocalEntity

@Dao
interface CategoryDao {
    @Query("select * from CategoryTable")
    suspend fun getAllCategoriesList(): List<CategoryLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoriesList(categories: List<CategoryLocalEntity>): List<Long>

    @Query("delete from CategoryTable")
    suspend fun clearCategoriesListCash():Int
}