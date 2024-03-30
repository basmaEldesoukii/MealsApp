package com.basma.homepage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basma.homepage.data.local.entity.AnnouncementLocalEntity

@Dao
interface AnnouncementDao {
    @Query("select * from AnnouncementTable")
    suspend fun getAllAnnouncementsList(): List<AnnouncementLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnouncementsList(announcements: List<AnnouncementLocalEntity>): List<Long>

    @Query("delete from AnnouncementTable")
    suspend fun clearAnnouncementsListCash():Int
}