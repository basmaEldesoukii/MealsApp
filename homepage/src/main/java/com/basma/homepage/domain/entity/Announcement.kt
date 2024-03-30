package com.basma.homepage.domain.entity

data class Announcement(
    val Category: Category,
    val Meal: Meal,
    val id: Int,
    val strThumb: String
)