package com.basma.meal_details

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.basma.meal_details.TestDataGenerator.Companion.mealDetailsMock
import com.basma.meal_details.presentation.screen.MealDetailsScreen
import com.basma.meal_details.presentation.viewmodel.MealDetailsContract
import com.basma.meal_details.presentation.viewmodel.MealDetailsViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test

class MealDetailsScreenUiTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    private val viewModel = mockk<MealDetailsViewModel>()

    @Test
    fun testMealDetailsLoadingState() {
        coEvery {
            viewModel.setIntent(
                MealDetailsContract.MealDetailsIntent.OnFetchMealDetails(
                    TestDataGenerator.mealId1.toString()
                )
            )
        } answers {
            val currentState = MealDetailsContract.MealDetailsState(
                mealDetailsState = MealDetailsContract.MealDetailsDataState.Loading
            )
            val stateFlow: MutableStateFlow<MealDetailsContract.MealDetailsState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            every { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            MealDetailsScreen(
                navController = mockk(),
                viewModel = viewModel,
                mealId = TestDataGenerator.mealId1.toString()
            )
        }

        // Verify that ProgressComponent is displayed
        composeTestRule
            .onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo))
            .assertIsDisplayed()
    }

    @Test
    fun testMealDetailsSuccessState() {
        coEvery {
            viewModel.setIntent(
                MealDetailsContract.MealDetailsIntent.OnFetchMealDetails(
                    TestDataGenerator.mealId1.toString()
                )
            )
        } answers {
            val currentState = MealDetailsContract.MealDetailsState(
                mealDetailsState = MealDetailsContract.MealDetailsDataState.Success(mealDetailsMock)
            )
            val stateFlow: MutableStateFlow<MealDetailsContract.MealDetailsState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            coEvery { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            MealDetailsScreen(
                navController = mockk(),
                viewModel = viewModel,
                mealId = TestDataGenerator.mealId1.toString()
            )
        }

        composeTestRule.onNodeWithText("Mock Meal").assertIsDisplayed()
    }

    @Test
    fun testMealDetailsErrorState() {
        val errorMsg = "Failed to load data"

        coEvery {
            viewModel.setIntent(
                MealDetailsContract.MealDetailsIntent.OnFetchMealDetails(
                    TestDataGenerator.mealId1.toString()
                )
            )
        } answers {
            val currentState = MealDetailsContract.MealDetailsState(
                mealDetailsState = MealDetailsContract.MealDetailsDataState.Error(errorMsg)
            )
            val stateFlow: MutableStateFlow<MealDetailsContract.MealDetailsState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            coEvery { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            MealDetailsScreen(
                navController = mockk(),
                viewModel = viewModel,
                TestDataGenerator.mealId1.toString()
            )
        }

        // Verify that ErrorComponent is displayed with the error message
        composeTestRule.onNodeWithText(errorMsg).assertIsDisplayed()
    }
}