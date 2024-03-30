package com.basma.homepage.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HomePageDao {
    @Query("select * from HomePageTable")
    suspend fun getHomePageData(): HomePageLocalEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomePageData(data: HomePageLocalEntity): Long

    @Query("delete from HomePageTable")
    suspend fun clearHomePageDataCash(): Int
}