package com.basma.meal_details.data.repo

import com.basma.common.utils.Mapper
import com.basma.meal_details.data.local.MealDetailsLocalEntity
import com.basma.meal_details.domain.entity.Meal
import javax.inject.Inject

class MealDetailsDataMapper @Inject constructor() :
    Mapper<Meal, MealDetailsLocalEntity> {
    override fun from(i: Meal?): MealDetailsLocalEntity {
        return MealDetailsLocalEntity(
            idMeal = i?.idMeal ?: "0",
            strArea = i?.strArea ?: "",
            strCategory = i?.strCategory ?: "",
            strIngredient1 = i?.strIngredient1 ?: "",
            strIngredient10 = i?.strIngredient10 ?: "",
            strIngredient11 = i?.strIngredient11 ?: "",
            strIngredient12 = i?.strIngredient12 ?: "",
            strIngredient13 = i?.strIngredient13 ?: "",
            strIngredient14 = i?.strIngredient14 ?: "",
            strIngredient15 = i?.strIngredient15 ?: "",
            strIngredient2 = i?.strIngredient2 ?: "",
            strIngredient3 = i?.strIngredient3 ?: "",
            strIngredient4 = i?.strIngredient4 ?: "",
            strIngredient5 = i?.strIngredient5 ?: "",
            strIngredient6 = i?.strIngredient6 ?: "",
            strIngredient7 = i?.strIngredient7 ?: "",
            strIngredient8 = i?.strIngredient8 ?: "",
            strIngredient9 = i?.strIngredient9 ?: "",
            strInstructions = i?.strInstructions ?: "",
            strMeal = i?.strMeal ?: "",
            strMealThumb = i?.strMealThumb ?: "",
            strMeasure1 = i?.strMeasure1 ?: "",
            strMeasure10 = i?.strMeasure10 ?: "",
            strMeasure11 = i?.strMeasure11 ?: "",
            strMeasure12 = i?.strMeasure12 ?: "",
            strMeasure13 = i?.strMeasure13 ?: "",
            strMeasure14 = i?.strMeasure14 ?: "",
            strMeasure15 = i?.strMeasure15 ?: "",
            strMeasure2 = i?.strMeasure2 ?: "",
            strMeasure3 = i?.strMeasure3 ?: "",
            strMeasure4 = i?.strMeasure4 ?: "",
            strMeasure5 = i?.strMeasure5 ?: "",
            strMeasure6 = i?.strMeasure6 ?: "",
            strMeasure7 = i?.strMeasure7 ?: "",
            strMeasure8 = i?.strMeasure8 ?: "",
            strMeasure9 = i?.strMeasure9 ?: "",
            strTags = i?.strTags ?: "",
            strYoutube = i?.strYoutube ?: ""
        )
    }

    override fun to(o: MealDetailsLocalEntity?): Meal {
        return Meal(
            idMeal = o?.idMeal ?: "0",
            strArea = o?.strArea ?: "",
            strCategory = o?.strCategory ?: "",
            strIngredient1 = o?.strIngredient1 ?: "",
            strIngredient10 = o?.strIngredient10 ?: "",
            strIngredient11 = o?.strIngredient11 ?: "",
            strIngredient12 = o?.strIngredient12 ?: "",
            strIngredient13 = o?.strIngredient13 ?: "",
            strIngredient14 = o?.strIngredient14 ?: "",
            strIngredient15 = o?.strIngredient15 ?: "",
            strIngredient2 = o?.strIngredient2 ?: "",
            strIngredient3 = o?.strIngredient3 ?: "",
            strIngredient4 = o?.strIngredient4 ?: "",
            strIngredient5 = o?.strIngredient5 ?: "",
            strIngredient6 = o?.strIngredient6 ?: "",
            strIngredient7 = o?.strIngredient7 ?: "",
            strIngredient8 = o?.strIngredient8 ?: "",
            strIngredient9 = o?.strIngredient9 ?: "",
            strInstructions = o?.strInstructions ?: "",
            strMeal = o?.strMeal ?: "",
            strMealThumb = o?.strMealThumb ?: "",
            strMeasure1 = o?.strMeasure1 ?: "",
            strMeasure10 = o?.strMeasure10 ?: "",
            strMeasure11 = o?.strMeasure11 ?: "",
            strMeasure12 = o?.strMeasure12 ?: "",
            strMeasure13 = o?.strMeasure13 ?: "",
            strMeasure14 = o?.strMeasure14 ?: "",
            strMeasure15 = o?.strMeasure15 ?: "",
            strMeasure2 = o?.strMeasure2 ?: "",
            strMeasure3 = o?.strMeasure3 ?: "",
            strMeasure4 = o?.strMeasure4 ?: "",
            strMeasure5 = o?.strMeasure5 ?: "",
            strMeasure6 = o?.strMeasure6 ?: "",
            strMeasure7 = o?.strMeasure7 ?: "",
            strMeasure8 = o?.strMeasure8 ?: "",
            strMeasure9 = o?.strMeasure9 ?: "",
            strTags = o?.strTags ?: "",
            strYoutube = o?.strYoutube ?: ""
        )
    }
}