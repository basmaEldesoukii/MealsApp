package com.basma.mealsapp.di_modules

import android.content.Context
import androidx.room.Room
import com.basma.homepage.data.local.HomePageLocalDatabase
import com.basma.meal_details.data.local.MealDetailsDatabase
import com.basma.meals_list.data.local.MealsListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideHomePageDatabase(@ApplicationContext application: Context): HomePageLocalDatabase {
        return Room
            .databaseBuilder(application, HomePageLocalDatabase::class.java, "homepage_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideHomePageDao(homePageLocalDataBase: HomePageLocalDatabase) =
        homePageLocalDataBase.homePageDao()

    @Provides
    @Singleton
    fun provideMealsListDatabase(@ApplicationContext application: Context): MealsListDatabase {
        return Room
            .databaseBuilder(application, MealsListDatabase::class.java, "meals_list_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideMealsListDao(mealsListDataBase: MealsListDatabase) =
        mealsListDataBase.mealsListDao()

    @Provides
    @Singleton
    fun provideMealDetailsDatabase(@ApplicationContext application: Context): MealDetailsDatabase {
        return Room
            .databaseBuilder(application, MealDetailsDatabase::class.java, "meal_details_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMealDetailsDao(mealDetailsDataBase: MealDetailsDatabase) =
        mealDetailsDataBase.mealDetailsDao()
}