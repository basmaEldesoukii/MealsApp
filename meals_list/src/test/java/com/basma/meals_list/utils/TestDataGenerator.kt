package com.basma.meals_list.utils

import com.basma.meals_list.data.local.MealLocalEntity
import com.basma.meals_list.domain.entity.Meal
import com.basma.meals_list.domain.entity.MealsListDataModel

class TestDataGenerator {
    companion object{

        const val categoryType = "Beef"
        const val mealId1 = 1
        private const val mealId2 = 2
        private const val mealId3 = 3


        // Data for UseCase Test
        fun generateMealsList(): List<Meal> {
            return listOf(
                generateMealItem(mealId1),
                generateMealItem(mealId2),
                generateMealItem(mealId3)
            )
        }

        fun generateMealItem(mealId: Int): Meal{
            return Meal(
                idMeal = mealId.toString(),
                strMeal = "Mock Beef",
                strMealThumb = "https://www.example.com/beef.png"
            )
        }
        //endOfRegion

        //Data for LocalData Test
        fun generateListOfLocalMealItem(): List<MealLocalEntity> {
            return listOf(
                generateLocalMealItem(mealId1),
                generateLocalMealItem(mealId2),
                generateLocalMealItem(mealId3)
            )
        }

        private fun generateLocalMealItem(mealId: Int): MealLocalEntity {
            return MealLocalEntity(
                idMeal = mealId.toString(),
                strMeal = "Mock Beef",
                strMealThumb = "https://www.example.com/beef.png"
            )
        }
        //endOfRegion

        // Data for UseCase Test
        fun generateListOfRemoteMealItem(): MealsListDataModel {
            return MealsListDataModel(
                generateMealsList()
            )
        }
        //endOfRegion
    }
}