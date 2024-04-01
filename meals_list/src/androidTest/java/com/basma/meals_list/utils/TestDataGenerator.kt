package com.basma.meals_list.utils

import com.basma.meals_list.domain.entity.Meal

class TestDataGenerator {
    companion object {
        const val categoryType = "Beef"
        private const val mealId1 = 1
        private const val mealId2 = 2
        private const val mealId3 = 3

        val mealsListMock = listOf(
            generateMealItem(mealId1),
            generateMealItem(mealId2),
            generateMealItem(mealId3)
        )

        private fun generateMealItem(mealId: Int): Meal {
            return Meal(
                idMeal = mealId.toString(),
                strMeal = "Mock Beef",
                strMealThumb = "https://www.example.com/beef.png"
            )
        }
    }
}