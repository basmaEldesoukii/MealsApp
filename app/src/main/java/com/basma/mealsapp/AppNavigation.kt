package com.basma.mealsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.basma.homepage.presentation.screen.HomePageScreen
import com.basma.homepage.presentation.viewmodel.HomePageViewModel
import com.basma.meal_details.presentation.screen.MealDetailsScreen
import com.basma.meal_details.presentation.viewmodel.MealDetailsViewModel
import com.basma.meals_list.presentation.screen.MealsListScreen
import com.basma.meals_list.presentation.viewmodel.MealsListViewModel
import com.basma.mealsapp.Navigation.Args.CATEGORY_TYPE
import com.basma.mealsapp.Navigation.Args.MEAL_ID

@Composable
fun AppNavigation(
    homePageViewModel: HomePageViewModel,
    mealsListViewModel: MealsListViewModel,
    mealDetailsViewModel: MealDetailsViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.HOME_PAGE
    ) {

        composable(
            route = Navigation.Routes.HOME_PAGE
        ) {
            HomePageScreen(
                navController = navController,
                viewModel = homePageViewModel
            )
        }

        composable(
            route = Navigation.Routes.MEALS_LIST,
            arguments = listOf(navArgument(name = CATEGORY_TYPE) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val categoryType =
                requireNotNull(backStackEntry.arguments?.getString(CATEGORY_TYPE)) { "Category type is required as an argument" }
            MealsListScreen(
                navController = navController,
                viewModel = mealsListViewModel,
                categoryType = categoryType
            )
        }

        composable(
            route = Navigation.Routes.MEAL_DETAILS,
            arguments = listOf(navArgument(name = MEAL_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val mealID =
                requireNotNull(backStackEntry.arguments?.getInt(MEAL_ID)) { "Meal ID is required as an argument" }
            MealDetailsScreen(
                navController = navController,
                viewModel = mealDetailsViewModel,
                mealId = mealID
            )
        }
    }
}

object Navigation {

    object Args {
        const val CATEGORY_TYPE = "category_type"
        const val MEAL_ID = "meal_id"
    }

    object Routes {
        const val HOME_PAGE = "home_page"
        const val MEALS_LIST = "meals_list/{$CATEGORY_TYPE}"
        const val MEAL_DETAILS = "meal_details/{$MEAL_ID}"
    }

}
