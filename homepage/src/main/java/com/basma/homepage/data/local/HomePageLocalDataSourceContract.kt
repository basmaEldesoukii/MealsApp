package com.basma.homepage.data.local

import com.basma.homepage.data.local.entity.AnnouncementLocalEntity
import com.basma.homepage.data.local.entity.CategoryLocalEntity
import com.basma.homepage.data.local.entity.IngredientLocalEntity
import com.basma.homepage.data.local.entity.MealLocalEntity

interface HomePageLocalDataSourceContract {
    //Categories
    suspend fun getAllCategoriesListFromDataBase(): List<CategoryLocalEntity>
    suspend fun insertCategoriesList(categories: List<CategoryLocalEntity>): List<Long>
    suspend fun clearCategoriesListCashed(): Int

    //Meals
    suspend fun getAllMealsListFromDataBase(): List<MealLocalEntity>
    suspend fun insertMealsList(meals: List<MealLocalEntity>): List<Long>
    suspend fun clearMealsListCashed(): Int

    //Ingredients
    suspend fun getAllIngredientsListFromDataBase(): List<IngredientLocalEntity>
    suspend fun insertIngredientsList(ingredients: List<IngredientLocalEntity>): List<Long>
    suspend fun clearIngredientsListCashed(): Int

    //Announcements
    suspend fun getAllAnnouncementsListFromDataBase(): List<AnnouncementLocalEntity>
    suspend fun insertAnnouncementsList(announcements: List<AnnouncementLocalEntity>): List<Long>
    suspend fun clearAnnouncementsListCashed(): Int
}