package com.basma.homepage.data.local

import com.basma.homepage.data.local.dao.AnnouncementDao
import com.basma.homepage.data.local.dao.CategoryDao
import com.basma.homepage.data.local.dao.IngredientDao
import com.basma.homepage.data.local.dao.MealDao
import com.basma.homepage.data.local.entity.AnnouncementLocalEntity
import com.basma.homepage.data.local.entity.CategoryLocalEntity
import com.basma.homepage.data.local.entity.IngredientLocalEntity
import com.basma.homepage.data.local.entity.MealLocalEntity
import javax.inject.Inject

class HomePageLocalDataSourceImp @Inject constructor(
    private val categoryDao: CategoryDao,
    private val mealDao: MealDao,
    private val ingredientDao: IngredientDao,
    private val announcementDao: AnnouncementDao
): HomePageLocalDataSourceContract {
    //Categories
    override suspend fun getAllCategoriesListFromDataBase(): List<CategoryLocalEntity> {
        return categoryDao.getAllCategoriesList()
    }

    override suspend fun insertCategoriesList(categories: List<CategoryLocalEntity>): List<Long> {
        return categoryDao.insertCategoriesList(categories)
    }

    override suspend fun clearCategoriesListCashed(): Int {
        return categoryDao.clearCategoriesListCash()
    }

    //Meals
    override suspend fun getAllMealsListFromDataBase(): List<MealLocalEntity> {
        return mealDao.getAllMealsList()
    }

    override suspend fun insertMealsList(meals: List<MealLocalEntity>): List<Long> {
        return mealDao.insertMealsList(meals)
    }

    override suspend fun clearMealsListCashed(): Int {
        return mealDao.clearMealsListCash()
    }

    //Ingredients
    override suspend fun getAllIngredientsListFromDataBase(): List<IngredientLocalEntity> {
        return ingredientDao.getAllIngredientsList()
    }

    override suspend fun insertIngredientsList(ingredients: List<IngredientLocalEntity>): List<Long> {
        return ingredientDao.insertIngredientsList(ingredients)
    }

    override suspend fun clearIngredientsListCashed(): Int {
        return ingredientDao.clearIngredientsListCash()
    }


    //Announcements
    override suspend fun getAllAnnouncementsListFromDataBase(): List<AnnouncementLocalEntity> {
        return announcementDao.getAllAnnouncementsList()
    }

    override suspend fun insertAnnouncementsList(announcements: List<AnnouncementLocalEntity>): List<Long> {
        return announcementDao.insertAnnouncementsList(announcements)
    }

    override suspend fun clearAnnouncementsListCashed(): Int {
        return announcementDao.clearAnnouncementsListCash()
    }
}