package com.basma.meals_list.data.repo

import com.basma.common.utils.Mapper
import com.basma.meals_list.data.local.MealLocalEntity
import com.basma.meals_list.domain.entity.Meal
import javax.inject.Inject

class MealsListDataMapper @Inject constructor() :
    Mapper<Meal, MealLocalEntity> {
    override fun from(i: Meal?): MealLocalEntity {
        return MealLocalEntity(
            idMeal = i?.idMeal ?: "0",
            strMeal = i?.strMeal ?: "",
            strMealThumb = i?.strMealThumb ?: ""
        )
    }

    override fun to(o: MealLocalEntity?): Meal {
        return Meal(
            idMeal = o?.idMeal ?: "0",
            strMeal = o?.strMeal ?: "",
            strMealThumb = o?.strMealThumb ?: ""
        )
    }
}