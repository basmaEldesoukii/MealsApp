package com.basma.homepage.data.repo

import com.basma.common.utils.Mapper
import com.basma.common.utils.Resource
import com.basma.homepage.data.local.HomePageLocalDataSourceContract
import com.basma.homepage.data.local.entity.AnnouncementLocalEntity
import com.basma.homepage.data.local.entity.CategoryLocalEntity
import com.basma.homepage.data.local.entity.IngredientLocalEntity
import com.basma.homepage.data.local.entity.MealLocalEntity
import com.basma.homepage.data.remote.HomePageRemoteDataSourceContract
import com.basma.homepage.domain.contract.HomePageRepositoryContract
import com.basma.homepage.domain.entity.Announcement
import com.basma.homepage.domain.entity.Category
import com.basma.homepage.domain.entity.Data
import com.basma.homepage.domain.entity.Ingredient
import com.basma.homepage.domain.entity.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomePageRepositoryImp @Inject constructor(
    private val localDataSource: HomePageLocalDataSourceContract,
    private val remoteDataSource: HomePageRemoteDataSourceContract,
    private val categoryDataMapper: Mapper<Category, CategoryLocalEntity>,
    private val mealDataMapper: Mapper<Meal, MealLocalEntity>,
    private val ingredientDataMapper: Mapper<Ingredient, IngredientLocalEntity>,
    private val announcementDataMapper: Mapper<Announcement, AnnouncementLocalEntity>
): HomePageRepositoryContract {
    override suspend fun getHomePageData(): Flow<Resource<Data>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val response = remoteDataSource.getHomePageData()
                // Emit data
                emit(Resource.Success(response.Data))

                // Save categories to local
                val categoriesData = response.Data.DynamicCollectionViewModel.firstOrNull {
                    it.Type == "Category"
                }?.Categories
                localDataSource.insertCategoriesList(categoryDataMapper.fromList(categoriesData))

                // Save meals to local
                val mealsData = response.Data.DynamicCollectionViewModel.firstOrNull {
                    it.Type == "Product"
                }?.Meals
                localDataSource.insertMealsList(mealDataMapper.fromList(mealsData))

                // Save ingredient to local
                val ingredientsData = response.Data.DynamicCollectionViewModel.firstOrNull {
                    it.Type == "Ingredients"
                }?.Ingredients
                localDataSource.insertIngredientsList(ingredientDataMapper.fromList(ingredientsData))

                // Save announcements to local
                val announcementsData = response.Data.DynamicCollectionViewModel.firstOrNull {
                    it.Type == "Product"
                }?.Announcements
                localDataSource.insertAnnouncementsList(announcementDataMapper.fromList(announcementsData))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails
                try {
                    // Get categoriesData from LocalDataSource and emit it
                    val categoriesData = localDataSource.getAllCategoriesListFromDataBase()
                    val mealsData = localDataSource.getAllMealsListFromDataBase()
                    val ingredientsData = localDataSource.getAllIngredientsListFromDataBase()
                    val announcementsData = localDataSource.getAllAnnouncementsListFromDataBase()
                    // TODO: Do I really need multiple tables in db?
                    //emit(Resource.Success(categoriesData.toList(categoriesData)))


                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}