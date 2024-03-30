package com.basma.mealsapp.di_modules

import com.basma.homepage.domain.contract.HomePageRepositoryContract
import com.basma.homepage.domain.usecase.GetHomePageDataUseCase
import com.basma.meal_details.domain.contract.MealDetailsRepositoryContract
import com.basma.meal_details.domain.usecase.GetMealDetailsUseCase
import com.basma.meals_list.domain.contract.MealsListRepositoryContract
import com.basma.meals_list.domain.usecase.GetMealsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHomePageDataUseCase(
        repository: HomePageRepositoryContract
    ): GetHomePageDataUseCase {
        return GetHomePageDataUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMealsListUseCase(
        repository: MealsListRepositoryContract
    ): GetMealsListUseCase {
        return GetMealsListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMealDetailsUseCase(
        repository: MealDetailsRepositoryContract,
    ): GetMealDetailsUseCase {
        return GetMealDetailsUseCase(repository)
    }
}