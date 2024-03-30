package com.basma.mealsapp.di_modules

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.HomePageLocalEntity
import com.basma.homepage.data.repo.HomePageDataMapper
import com.basma.homepage.domain.entity.HomePageDataModel
import com.basma.meal_details.data.local.MealDetailsLocalEntity
import com.basma.meal_details.data.repo.MealDetailsDataMapper
import com.basma.meal_details.domain.entity.MealDetails
import com.basma.meals_list.data.local.MealLocalEntity
import com.basma.meals_list.data.repo.MealsListDataMapper
import com.basma.meals_list.domain.entity.Meal
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindsLocalHomePageDataMapper(mapper: HomePageDataMapper): Mapper<HomePageDataModel, HomePageLocalEntity>

    @Binds
    abstract fun bindsLocalMealsListDataMapper(mapper: MealsListDataMapper): Mapper<Meal, MealLocalEntity>

    @Binds
    abstract fun bindsLocalMealDetailsItemMapper(mapper: MealDetailsDataMapper): Mapper<MealDetails, MealDetailsLocalEntity>
}