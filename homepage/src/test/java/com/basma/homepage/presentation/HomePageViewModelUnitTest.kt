package com.basma.homepage.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.homepage.utils.MainCoroutineRule
import com.basma.common.utils.Resource
import com.basma.homepage.domain.usecase.GetHomePageDataUseCase
import com.basma.homepage.presentation.viewmodel.HomePageContract
import com.basma.homepage.presentation.viewmodel.HomePageViewModel
import com.basma.homepage.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class HomePageViewModelUnitTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getHomePageDataUseCase: GetHomePageDataUseCase

    private lateinit var homePageViewModel: HomePageViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        homePageViewModel = HomePageViewModel(
            getHomePageDataUseCase = getHomePageDataUseCase
        )
    }

    @Test
    fun test_fetch_homepage_data_success() = runTest {
        val homePageData = TestDataGenerator.generateHomePageData()
        val dataFlow = flowOf(Resource.Success(homePageData))

        // Given
        coEvery { getHomePageDataUseCase.invoke() } returns dataFlow

        // When && Assertions
        homePageViewModel.uiState.test {
            homePageViewModel.setIntent(HomePageContract.HomePageIntent.OnFetchHomePageData)
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                HomePageContract.HomePageState(
                    homePageDataState = HomePageContract.HomePageDataState.Loading
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.homePageDataState as HomePageContract.HomePageDataState.Success).data
            Truth.assertThat(expected).isEqualTo(
                HomePageContract.HomePageState(
                    homePageDataState = HomePageContract.HomePageDataState.Success(homePageData.Data)
                )
            )
            Truth.assertThat(expectedData).isEqualTo(homePageData.Data)

            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // Then
        coVerify { getHomePageDataUseCase.invoke() }
    }

    @Test
    fun test_fetch_homepage_data_fail() = runTest {
        val dataErrorFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getHomePageDataUseCase.invoke() } returns dataErrorFlow

        // When && Assertions (UiState)
        homePageViewModel.uiState.test {
            // Call method inside of this scope
            homePageViewModel.setIntent(HomePageContract.HomePageIntent.OnFetchHomePageData)
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                HomePageContract.HomePageState(
                    homePageDataState = HomePageContract.HomePageDataState.Loading
                )
            )

            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.homePageDataState as HomePageContract.HomePageDataState.Error).errorMsg
            Truth.assertThat(expected).isEqualTo(
                HomePageContract.HomePageState(
                    homePageDataState = HomePageContract.HomePageDataState.Error("error")
                )
            )
            Truth.assertThat(expectedData).isEqualTo("error")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then
        coVerify { getHomePageDataUseCase.invoke() }
    }
}