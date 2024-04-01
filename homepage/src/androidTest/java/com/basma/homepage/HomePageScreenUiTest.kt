package com.basma.homepage

import androidx.compose.ui.semantics.SemanticsProperties.ProgressBarRangeInfo
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.basma.homepage.presentation.screen.HomePageScreen
import com.basma.homepage.presentation.viewmodel.HomePageContract
import com.basma.homepage.presentation.viewmodel.HomePageViewModel
import com.basma.homepage.utils.TestDataGenerator
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomePageScreenUiTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    private val viewModel = mockk<HomePageViewModel>()

    @Test
    fun testHomePageLoadingState() {
        coEvery {
            viewModel.setIntent(HomePageContract.HomePageIntent.OnFetchHomePageData)
        } answers {
            val currentState = HomePageContract.HomePageState(
                homePageDataState = HomePageContract.HomePageDataState.Loading
            )
            val stateFlow: MutableStateFlow<HomePageContract.HomePageState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            every { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            HomePageScreen(navController = mockk(), viewModel = viewModel)
        }

        // Verify that ProgressComponent is displayed
        composeTestRule
            .onNode(SemanticsMatcher.keyIsDefined(ProgressBarRangeInfo))
            .assertIsDisplayed()
    }

    @Test
    fun testHomePageSuccessState() {
        coEvery {
            viewModel.setIntent(HomePageContract.HomePageIntent.OnFetchHomePageData)
        } answers {
            val currentState = HomePageContract.HomePageState(
                homePageDataState = HomePageContract.HomePageDataState.Success(TestDataGenerator.dataMock)
            )
            val stateFlow: MutableStateFlow<HomePageContract.HomePageState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            coEvery { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            HomePageScreen(navController = mockk(), viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Top Categories").assertIsDisplayed()
    }

    @Test
    fun testHomePageErrorState() {
        val errorMsg = "Failed to load data"

        coEvery {
            viewModel.setIntent(HomePageContract.HomePageIntent.OnFetchHomePageData)
        } answers {
            val currentState = HomePageContract.HomePageState(
                homePageDataState = HomePageContract.HomePageDataState.Error(errorMsg)
            )
            val stateFlow: MutableStateFlow<HomePageContract.HomePageState> =
                MutableStateFlow(currentState)
            // Stub the behavior of the ViewModel
            coEvery { viewModel.uiState } returns stateFlow.asStateFlow()
        }

        // Launch the Composable
        composeTestRule.setContent {
            HomePageScreen(navController = mockk(), viewModel = viewModel)
        }

        // Verify that ErrorComponent is displayed with the error message
        composeTestRule.onNodeWithText(errorMsg).assertIsDisplayed()
    }
}