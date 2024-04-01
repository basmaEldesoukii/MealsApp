package com.basma.meal_details.utils

import com.basma.meal_details.data.local.MealDetailsLocalEntity
import com.basma.meal_details.domain.entity.Meal
import com.basma.meal_details.domain.entity.MealDetailsDataModel

class TestDataGenerator {
    companion object {
        const val mealId1 = 1

        // Data for UseCase Test & RemoteData Test
        fun generateListOfMealDetailsItem(): MealDetailsDataModel {
            return MealDetailsDataModel(
                listOf(
                    generateMealDetailsItem(mealId1)
                )
            )
        }

        fun generateMealDetailsItem(mealId: Int): Meal {
            return Meal(
                idMeal = mealId.toString(),
                strArea = "Mock Area",
                strCategory = "Mock Category",
                strIngredient1 = "Ingredient 1",
                strIngredient10 = "Ingredient 10",
                strIngredient11 = "Ingredient 11",
                strIngredient12 = "Ingredient 12",
                strIngredient13 = "Ingredient 13",
                strIngredient14 = "Ingredient 14",
                strIngredient15 = "Ingredient 15",
                strIngredient2 = "Ingredient 2",
                strIngredient3 = "Ingredient 3",
                strIngredient4 = "Ingredient 4",
                strIngredient5 = "Ingredient 5",
                strIngredient6 = "Ingredient 6",
                strIngredient7 = "Ingredient 7",
                strIngredient8 = "Ingredient 8",
                strIngredient9 = "Ingredient 9",
                strInstructions = "Mock instructions for preparing the meal.",
                strMeal = "Mock Meal",
                strMealThumb = "https://www.example.com/mock_meal.jpg",
                strMeasure1 = "1 cup",
                strMeasure10 = "10 grams",
                strMeasure11 = "11 grams",
                strMeasure12 = "12 grams",
                strMeasure13 = "13 grams",
                strMeasure14 = "14 grams",
                strMeasure15 = "15 grams",
                strMeasure2 = "2 tablespoons",
                strMeasure3 = "3 teaspoons",
                strMeasure4 = "4 cups",
                strMeasure5 = "5 slices",
                strMeasure6 = "6 pieces",
                strMeasure7 = "7 tablespoons",
                strMeasure8 = "8 teaspoons",
                strMeasure9 = "9 cups",
                strTags = "Mock, Tags, Here",
                strYoutube = "https://www.youtube.com/watch?v=mockvideo",
                strCreativeCommonsConfirmed = "",
                strDrinkAlternate = "",
                strImageSource = "",
                strIngredient16 = "",
                strIngredient17 = "",
                strIngredient18 = "",
                strIngredient19 = "",
                strIngredient20 = "",
                strMeasure16 = "",
                strMeasure17 = "",
                strMeasure18 = "",
                strMeasure19 = "",
                strMeasure20 = "",
                strSource = "",
                dateModified = ""
            )
        }
        //endOfRegion

        //Data for LocalData Test
        fun generateMealDetailsLocalData(): MealDetailsLocalEntity {
            return MealDetailsLocalEntity(
                idMeal = "1",
                strArea = "Mock Area",
                strCategory = "Mock Category",
                strIngredient1 = "Ingredient 1",
                strIngredient10 = "Ingredient 10",
                strIngredient11 = "Ingredient 11",
                strIngredient12 = "Ingredient 12",
                strIngredient13 = "Ingredient 13",
                strIngredient14 = "Ingredient 14",
                strIngredient15 = "Ingredient 15",
                strIngredient2 = "Ingredient 2",
                strIngredient3 = "Ingredient 3",
                strIngredient4 = "Ingredient 4",
                strIngredient5 = "Ingredient 5",
                strIngredient6 = "Ingredient 6",
                strIngredient7 = "Ingredient 7",
                strIngredient8 = "Ingredient 8",
                strIngredient9 = "Ingredient 9",
                strInstructions = "Mock instructions for preparing the meal.",
                strMeal = "Mock Meal",
                strMealThumb = "https://www.example.com/mock_meal.jpg",
                strMeasure1 = "1 cup",
                strMeasure10 = "10 grams",
                strMeasure11 = "11 grams",
                strMeasure12 = "12 grams",
                strMeasure13 = "13 grams",
                strMeasure14 = "14 grams",
                strMeasure15 = "15 grams",
                strMeasure2 = "2 tablespoons",
                strMeasure3 = "3 teaspoons",
                strMeasure4 = "4 cups",
                strMeasure5 = "5 slices",
                strMeasure6 = "6 pieces",
                strMeasure7 = "7 tablespoons",
                strMeasure8 = "8 teaspoons",
                strMeasure9 = "9 cups",
                strTags = "Mock, Tags, Here",
                strYoutube = "https://www.youtube.com/watch?v=mockvideo"
            )

        }
        //endOfRegion
    }
}