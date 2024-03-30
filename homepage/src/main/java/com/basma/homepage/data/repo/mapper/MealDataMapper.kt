package com.basma.homepage.data.repo.mapper

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.entity.MealLocalEntity
import com.basma.homepage.domain.entity.Meal
import javax.inject.Inject

class MealDataMapper @Inject constructor() : Mapper<Meal, MealLocalEntity> {

    override fun from(i: Meal?): MealLocalEntity {
        return MealLocalEntity(
            dateModified = i?.dateModified ?: "",
            idMeal = i?.idMeal ?: "",
            strArea = i?.strArea ?: "",
            strCategory = i?.strCategory ?: "",
            strCreativeCommonsConfirmed = i?.strCreativeCommonsConfirmed ?: "",
            strDrinkAlternate = i?.strDrinkAlternate ?: "",
            strImageSource = i?.strImageSource ?: "",
            strIngredient1 = i?.strIngredient1 ?: "",
            strIngredient2 = i?.strIngredient2 ?: "",
            strIngredient3 = i?.strIngredient3 ?: "",
            strIngredient4 = i?.strIngredient4 ?: "",
            strIngredient5 = i?.strIngredient5 ?: "",
            strIngredient6 = i?.strIngredient6 ?: "",
            strIngredient7 = i?.strIngredient7 ?: "",
            strIngredient8 = i?.strIngredient8 ?: "",
            strIngredient9 = i?.strIngredient9 ?: "",
            strIngredient10 = i?.strIngredient10 ?: "",
            strIngredient11 = i?.strIngredient11 ?: "",
            strIngredient12 = i?.strIngredient12 ?: "",
            strIngredient13 = i?.strIngredient13 ?: "",
            strIngredient14 = i?.strIngredient14 ?: "",
            strIngredient15 = i?.strIngredient15 ?: "",
            strIngredient16 = i?.strIngredient16 ?: "",
            strIngredient17 = i?.strIngredient17 ?: "",
            strIngredient18 = i?.strIngredient18 ?: "",
            strIngredient19 = i?.strIngredient19 ?: "",
            strIngredient20 = i?.strIngredient20 ?: "",
            strInstructions = i?.strInstructions ?: "",
            strMeal = i?.strMeal ?: "",
            strMealThumb = i?.strMealThumb ?: "",
            strMeasure1 = i?.strMeasure1 ?: "",
            strMeasure2 = i?.strMeasure2 ?: "",
            strMeasure3 = i?.strMeasure3 ?: "",
            strMeasure4 = i?.strMeasure4 ?: "",
            strMeasure5 = i?.strMeasure5 ?: "",
            strMeasure6 = i?.strMeasure6 ?: "",
            strMeasure7 = i?.strMeasure7 ?: "",
            strMeasure8 = i?.strMeasure8 ?: "",
            strMeasure9 = i?.strMeasure9 ?: "",
            strMeasure10 = i?.strMeasure10 ?: "",
            strMeasure11 = i?.strMeasure11 ?: "",
            strMeasure12 = i?.strMeasure12 ?: "",
            strMeasure13 = i?.strMeasure13 ?: "",
            strMeasure14 = i?.strMeasure14 ?: "",
            strMeasure15 = i?.strMeasure15 ?: "",
            strMeasure16 = i?.strMeasure16 ?: "",
            strMeasure17 = i?.strMeasure17 ?: "",
            strMeasure18 = i?.strMeasure18 ?: "",
            strMeasure19 = i?.strMeasure19 ?: "",
            strMeasure20 = i?.strMeasure20 ?: "",
            strSource = i?.strSource ?: "",
            strTags = i?.strTags ?: "",
            strYoutube = i?.strYoutube ?: ""
        )
    }

    override fun to(o: MealLocalEntity?): Meal {
        return Meal(
            dateModified = o?.dateModified ?: "",
            idMeal = o?.idMeal ?: "",
            strArea = o?.strArea ?: "",
            strCategory = o?.strCategory ?: "",
            strCreativeCommonsConfirmed = o?.strCreativeCommonsConfirmed ?: "",
            strDrinkAlternate = o?.strDrinkAlternate ?: "",
            strImageSource = o?.strImageSource ?: "",
            strIngredient1 = o?.strIngredient1 ?: "",
            strIngredient2 = o?.strIngredient2 ?: "",
            strIngredient3 = o?.strIngredient3 ?: "",
            strIngredient4 = o?.strIngredient4 ?: "",
            strIngredient5 = o?.strIngredient5 ?: "",
            strIngredient6 = o?.strIngredient6 ?: "",
            strIngredient7 = o?.strIngredient7 ?: "",
            strIngredient8 = o?.strIngredient8 ?: "",
            strIngredient9 = o?.strIngredient9 ?: "",
            strIngredient10 = o?.strIngredient10 ?: "",
            strIngredient11 = o?.strIngredient11 ?: "",
            strIngredient12 = o?.strIngredient12 ?: "",
            strIngredient13 = o?.strIngredient13 ?: "",
            strIngredient14 = o?.strIngredient14 ?: "",
            strIngredient15 = o?.strIngredient15 ?: "",
            strIngredient16 = o?.strIngredient16 ?: "",
            strIngredient17 = o?.strIngredient17 ?: "",
            strIngredient18 = o?.strIngredient18 ?: "",
            strIngredient19 = o?.strIngredient19 ?: "",
            strIngredient20 = o?.strIngredient20 ?: "",
            strInstructions = o?.strInstructions ?: "",
            strMeal = o?.strMeal ?: "",
            strMealThumb = o?.strMealThumb ?: "",
            strMeasure1 = o?.strMeasure1 ?: "",
            strMeasure2 = o?.strMeasure2 ?: "",
            strMeasure3 = o?.strMeasure3 ?: "",
            strMeasure4 = o?.strMeasure4 ?: "",
            strMeasure5 = o?.strMeasure5 ?: "",
            strMeasure6 = o?.strMeasure6 ?: "",
            strMeasure7 = o?.strMeasure7 ?: "",
            strMeasure8 = o?.strMeasure8 ?: "",
            strMeasure9 = o?.strMeasure9 ?: "",
            strMeasure10 = o?.strMeasure10 ?: "",
            strMeasure11 = o?.strMeasure11 ?: "",
            strMeasure12 = o?.strMeasure12 ?: "",
            strMeasure13 = o?.strMeasure13 ?: "",
            strMeasure14 = o?.strMeasure14 ?: "",
            strMeasure15 = o?.strMeasure15 ?: "",
            strMeasure16 = o?.strMeasure16 ?: "",
            strMeasure17 = o?.strMeasure17 ?: "",
            strMeasure18 = o?.strMeasure18 ?: "",
            strMeasure19 = o?.strMeasure19 ?: "",
            strMeasure20 = o?.strMeasure20 ?: "",
            strSource = o?.strSource ?: "",
            strTags = o?.strTags ?: "",
            strYoutube = o?.strYoutube ?: ""
        )
    }
}