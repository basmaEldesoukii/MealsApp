package com.basma.meals_list.data.local

import androidx.room.Database

@Database(
    entities = [MealLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MealsListDatabase {
    abstract fun mealsListDao(): MealsListDao
}