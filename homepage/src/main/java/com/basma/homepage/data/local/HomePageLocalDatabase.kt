package com.basma.homepage.data.local

import androidx.room.Database

@Database(
    entities = [HomePageLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HomePageLocalDatabase {
    abstract fun homePageDao(): HomePageDao
}