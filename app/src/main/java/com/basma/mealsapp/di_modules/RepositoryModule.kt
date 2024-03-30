package com.basma.mealsapp.di_modules

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.HomePageDao
import com.basma.homepage.data.local.HomePageLocalDataSourceImp
import com.basma.homepage.data.local.HomePageLocalEntity
import com.basma.homepage.data.remote.HomePageRemoteDataSourceImp
import com.basma.homepage.data.remote.HomePageRemoteServices
import com.basma.homepage.data.repo.HomePageRepositoryImp
import com.basma.homepage.domain.contract.HomePageRepositoryContract
import com.basma.homepage.domain.entity.HomePageDataModel
import com.basma.meal_details.data.local.MealDetailsDao
import com.basma.meal_details.data.local.MealDetailsLocalDataSourceImp
import com.basma.meal_details.data.local.MealDetailsLocalEntity
import com.basma.meal_details.data.remote.MealDetailsRemoteDataSourceImp
import com.basma.meal_details.data.remote.MealDetailsRemoteServices
import com.basma.meal_details.data.repo.MealDetailsRepositoryImp
import com.basma.meal_details.domain.contract.MealDetailsRepositoryContract
import com.basma.meal_details.domain.entity.MealDetails
import com.basma.meals_list.data.local.MealLocalEntity
import com.basma.meals_list.data.local.MealsListDao
import com.basma.meals_list.data.local.MealsListLocalDataSourceImp
import com.basma.meals_list.data.remote.MealsListRemoteDataSourceImp
import com.basma.meals_list.data.remote.MealsListRemoteServices
import com.basma.meals_list.data.repo.MealsListRepositoryImp
import com.basma.meals_list.domain.contract.MealsListRepositoryContract
import com.basma.meals_list.domain.entity.Meal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    //HomePage
    @Singleton
    @Provides
    fun provideHomePageLocalDataSource(
        homePageDao: HomePageDao,
    ) = HomePageLocalDataSourceImp(homePageDao)

    @Singleton
    @Provides
    fun provideHomePageRemoteDataSource(
        apiService: HomePageRemoteServices,
    ) = HomePageRemoteDataSourceImp(
        apiService
    )

    @Singleton
    @Provides
    fun provideHomePageRepository(
        localDataSource: HomePageLocalDataSourceImp,
        remoteDataSource: HomePageRemoteDataSourceImp,
        mapper: Mapper<HomePageDataModel, HomePageLocalEntity>
    ): HomePageRepositoryContract =
        HomePageRepositoryImp(
            localDataSource,
            remoteDataSource,
            mapper
        )

    //Meals List
    @Singleton
    @Provides
    fun provideMealsListLocalDataSource(
        mealsListDao: MealsListDao,
    ) = MealsListLocalDataSourceImp(mealsListDao)

    @Singleton
    @Provides
    fun provideMealsListRemoteDataSource(
        apiService: MealsListRemoteServices,
    ) = MealsListRemoteDataSourceImp(
        apiService
    )

    @Singleton
    @Provides
    fun provideMealsListRepository(
        localDataSource: MealsListLocalDataSourceImp,
        remoteDataSource: MealsListRemoteDataSourceImp,
        mapper: Mapper<Meal, MealLocalEntity>
    ): MealsListRepositoryContract =
        MealsListRepositoryImp(
            localDataSource,
            remoteDataSource,
            mapper
        )

    //Meals Details
    @Singleton
    @Provides
    fun provideMealDetailsLocalDataSource(
        mealDetailsDao: MealDetailsDao,
    ) = MealDetailsLocalDataSourceImp(mealDetailsDao)

    @Singleton
    @Provides
    fun provideMealDetailsRemoteDataSource(
        apiService: MealDetailsRemoteServices,
    ) = MealDetailsRemoteDataSourceImp(
        apiService
    )

    @Singleton
    @Provides
    fun provideMealDetailsRepository(
        localDataSource: MealDetailsLocalDataSourceImp,
        remoteDataSource: MealDetailsRemoteDataSourceImp,
        mapper: Mapper<MealDetails, MealDetailsLocalEntity>
    ): MealDetailsRepositoryContract =
        MealDetailsRepositoryImp(
            localDataSource,
            remoteDataSource,
            mapper
        )
}