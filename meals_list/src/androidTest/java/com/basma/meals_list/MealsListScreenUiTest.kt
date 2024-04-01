package com.basma.meals_list

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.basma.meals_list.presentation.screen.MealsListScreen
import com.basma.meals_list.presentation.viewmodel.MealsListContract
import com.basma.meals_list.presentation.viewmodel.MealsListViewModel
import com.basma.meals_list.utils.TestDataGenerator
import com.basma.meals_list.utils.TestDataGenerator.Companion.mealsListMock
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MealsListScreenUiTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    private val viewModel = mockk<MealsListViewModel>()

    @Test
    fun testMealsListLoadingState() {
        coEvery {
            viewModel.setIntent(
                MealsListContract.MealsListIntent.OnFetchMealsListData(
                    TestDataGenerator.categoryType
                )
            )
        } answers {
            val currentState = MealsListContract.MealsListState(
                mealsListState = MealsListContract.MealsListDataState.Loading
            )
            val stateFlow: MutableStateFlow<MealsListContract.MealsListState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            every { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            MealsListScreen(
                navController = mockk(),
                viewModel = viewModel,
                categoryType = TestDataGenerator.categoryType
            )
        }

        // Verify that ProgressComponent is displayed
        composeTestRule
            .onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.ProgressBarRangeInfo))
            .assertIsDisplayed()
    }

    @Test
    fun testMealsListSuccessState() {
        coEvery {
            viewModel.setIntent(
                MealsListContract.MealsListIntent.OnFetchMealsListData(
                    TestDataGenerator.categoryType
                )
            )
        } answers {
            val currentState = MealsListContract.MealsListState(
                mealsListState = MealsListContract.MealsListDataState.Success(mealsListMock)
            )
            val stateFlow: MutableStateFlow<MealsListContract.MealsListState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            coEvery { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            MealsListScreen(
                navController = mockk(),
                viewModel = viewModel,
                categoryType = TestDataGenerator.categoryType
            )
        }

        composeTestRule.onAllNodesWithText("Mock Beef").assertCountEquals(mealsListMock.size)
    }

    @Test
    fun testMealsListErrorState() {
        val errorMsg = "Failed to load data"

        coEvery {
            viewModel.setIntent(
                MealsListContract.MealsListIntent.OnFetchMealsListData(
                    TestDataGenerator.categoryType
                )
            )
        } answers {
            val currentState = MealsListContract.MealsListState(
                mealsListState = MealsListContract.MealsListDataState.Error(errorMsg)
            )
            val stateFlow: MutableStateFlow<MealsListContract.MealsListState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            coEvery { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            MealsListScreen(
                navController = mockk(),
                viewModel = viewModel,
                categoryType = TestDataGenerator.categoryType
            )
        }

        // Verify that ErrorComponent is displayed with the error message
        composeTestRule.onNodeWithText(errorMsg).assertIsDisplayed()
    }
}