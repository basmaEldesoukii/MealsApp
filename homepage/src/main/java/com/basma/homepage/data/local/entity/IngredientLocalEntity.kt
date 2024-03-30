package com.basma.homepage.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IngredientTable")
data class IngredientLocalEntity(
    @PrimaryKey
    val idIngredient: String,
    val strDescription: String,
    val strIngredient: String,
    val strType: Any
)
