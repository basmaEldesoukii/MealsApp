package com.basma.meal_details.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MealDetailsLocalEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MealDetailsDatabase : RoomDatabase() {
    abstract fun mealDetailsDao(): MealDetailsDao
}