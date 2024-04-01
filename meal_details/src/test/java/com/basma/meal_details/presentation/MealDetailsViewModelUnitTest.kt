package com.basma.meal_details.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.meal_details.domain.usecase.GetMealDetailsUseCase
import com.basma.meal_details.presentation.viewmodel.MealDetailsContract
import com.basma.meal_details.presentation.viewmodel.MealDetailsViewModel
import com.basma.meal_details.utils.MainCoroutineRule
import com.basma.meal_details.utils.TestDataGenerator
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
class MealDetailsViewModelUnitTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getMealDetailsUseCase: GetMealDetailsUseCase

    private lateinit var mealsListViewModel: MealDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        mealsListViewModel = MealDetailsViewModel(
            getMealDetailsUseCase = getMealDetailsUseCase
        )
    }

    @Test
    fun test_fetch_meal_details_success() = runTest {
        val mealDetails = TestDataGenerator.generateMealDetailsItem(TestDataGenerator.mealId1)
        val mealsFlow = flowOf(Resource.Success(mealDetails))

        // Given
        coEvery { getMealDetailsUseCase.invoke(TestDataGenerator.mealId1.toString()) } returns mealsFlow

        // When && Assertions
        mealsListViewModel.uiState.test {
            mealsListViewModel.setIntent(MealDetailsContract.MealDetailsIntent.OnFetchMealDetails(TestDataGenerator.mealId1.toString()))
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MealDetailsContract.MealDetailsState(
                    mealDetailsState = MealDetailsContract.MealDetailsDataState.Loading
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.mealDetailsState as MealDetailsContract.MealDetailsDataState.Success).meal
            Truth.assertThat(expected).isEqualTo(
                MealDetailsContract.MealDetailsState(
                    mealDetailsState = MealDetailsContract.MealDetailsDataState.Success(mealDetails)
                )
            )
            Truth.assertThat(expectedData).isEqualTo(mealDetails)

            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // Then
        coVerify { getMealDetailsUseCase.invoke(TestDataGenerator.mealId1.toString()) }
    }

    @Test
    fun test_fetch_meal_details_fail() = runTest {
        val mealsErrorFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getMealDetailsUseCase.invoke(TestDataGenerator.mealId1.toString()) } returns mealsErrorFlow

        // When && Assertions (UiState)
        mealsListViewModel.uiState.test {
            // Call method inside of this scope
            mealsListViewModel.setIntent(MealDetailsContract.MealDetailsIntent.OnFetchMealDetails(TestDataGenerator.mealId1.toString()))
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MealDetailsContract.MealDetailsState(
                    mealDetailsState = MealDetailsContract.MealDetailsDataState.Loading
                )
            )

            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.mealDetailsState as MealDetailsContract.MealDetailsDataState.Error).errorMsg
            Truth.assertThat(expected).isEqualTo(
                MealDetailsContract.MealDetailsState(
                    mealDetailsState = MealDetailsContract.MealDetailsDataState.Error("error")
                )
            )
            Truth.assertThat(expectedData).isEqualTo("error")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then
        coVerify { getMealDetailsUseCase.invoke(TestDataGenerator.mealId1.toString()) }
    }
}