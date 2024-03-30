package com.basma.homepage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [HomePageLocalEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class HomePageLocalDatabase: RoomDatabase() {
    abstract fun homePageDao(): HomePageDao
}