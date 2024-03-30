package com.basma.homepage.domain.entity

data class DynamicCollectionViewModel(
    val Announcements: List<Announcement>,
    val Categories: List<Category>,
    val Id: Int,
    val Ingredients: List<Ingredient>,
    val Meals: List<Meal>,
    val Order: Int,
    val Title: String,
    val Type: String,
    val Url: String
)