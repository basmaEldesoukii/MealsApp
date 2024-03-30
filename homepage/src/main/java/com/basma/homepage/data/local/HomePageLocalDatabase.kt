package com.basma.homepage.data.local

import androidx.room.Database
import com.basma.homepage.data.local.dao.AnnouncementDao
import com.basma.homepage.data.local.dao.CategoryDao
import com.basma.homepage.data.local.dao.IngredientDao
import com.basma.homepage.data.local.dao.MealDao
import com.basma.homepage.data.local.entity.AnnouncementLocalEntity
import com.basma.homepage.data.local.entity.CategoryLocalEntity
import com.basma.homepage.data.local.entity.IngredientLocalEntity
import com.basma.homepage.data.local.entity.MealLocalEntity

@Database(
    entities = [
        CategoryLocalEntity::class,
        MealLocalEntity::class,
        IngredientLocalEntity::class,
        AnnouncementLocalEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HomePageLocalDatabase {
    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao

    abstract fun ingredientDao(): IngredientDao

    abstract fun announcementDao(): AnnouncementDao
}