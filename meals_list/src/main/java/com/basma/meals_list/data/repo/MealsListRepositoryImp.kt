package com.basma.meals_list.data.repo

import com.basma.common.utils.Mapper
import com.basma.common.utils.Resource
import com.basma.meals_list.data.local.MealLocalEntity
import com.basma.meals_list.data.local.MealsListLocalDataSourceContract
import com.basma.meals_list.data.remote.MealsListRemoteDataSourceContract
import com.basma.meals_list.domain.contract.MealsListRepositoryContract
import com.basma.meals_list.domain.entity.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealsListRepositoryImp @Inject constructor(
    private val localDataSource: MealsListLocalDataSourceContract,
    private val remoteDataSource: MealsListRemoteDataSourceContract,
    private val mealsListDataMapper: Mapper<Meal, MealLocalEntity>

): MealsListRepositoryContract{
    override suspend fun getMealsList(categoryType: String): Flow<Resource<List<Meal>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val response = remoteDataSource.getMealsList(categoryType)
                emit(Resource.Success(response.meals))
                // Save to local
                localDataSource.insertMealsList(mealsListDataMapper.fromList(response.meals))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails, get data from LocalDataSource
                try {
                    val localData = localDataSource.getMealsListFromDataBase(categoryType)
                    emit(Resource.Success(mealsListDataMapper.toList(localData)))
                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}