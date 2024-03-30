package com.basma.meals_list.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MealLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MealsListDatabase: RoomDatabase() {
    abstract fun mealsListDao(): MealsListDao
}