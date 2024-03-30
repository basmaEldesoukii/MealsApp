package com.basma.homepage.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.basma.homepage.domain.entity.Data

@Entity(tableName = "HomePageTable")
data class HomePageLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val data: Data
)