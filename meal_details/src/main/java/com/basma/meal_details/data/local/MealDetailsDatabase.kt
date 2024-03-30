package com.basma.meal_details.data.local

import androidx.room.Database

@Database(
    entities = [MealDetailsLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MealDetailsDatabase {
    abstract fun mealDetailsDao(): MealDetailsDao
}