package com.basma.homepage.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.basma.homepage.domain.entity.Category
import com.basma.homepage.domain.entity.Meal

@Entity(tableName = "AnnouncementTable")
data class AnnouncementLocalEntity (
    val Category: Category,
    val Meal: Meal,
    @PrimaryKey
    val id: Int,
    val strThumb: String
)