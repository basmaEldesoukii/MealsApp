package com.basma.homepage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HomePageLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HomePageLocalDatabase: RoomDatabase() {
    abstract fun homePageDao(): HomePageDao
}