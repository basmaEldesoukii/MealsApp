package com.basma.meals_list.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.meals_list.domain.usecase.GetMealsListUseCase
import com.basma.meals_list.presentation.viewmodel.MealsListContract
import com.basma.meals_list.presentation.viewmodel.MealsListViewModel
import com.basma.meals_list.utils.MainCoroutineRule
import com.basma.meals_list.utils.TestDataGenerator
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
class MealsListViewModelUnitTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getMealsListUseCase: GetMealsListUseCase

    private lateinit var mealsListViewModel: MealsListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        mealsListViewModel = MealsListViewModel(
            getMealsListUseCase = getMealsListUseCase
        )
    }

    @Test
    fun test_fetch_meals_list_success() = runTest {
        val meals = TestDataGenerator.generateMealsList()
        val mealsFlow = flowOf(Resource.Success(meals))

        // Given
        coEvery { getMealsListUseCase.invoke(TestDataGenerator.categoryType) } returns mealsFlow

        // When && Assertions
        mealsListViewModel.uiState.test {
            mealsListViewModel.setIntent(
                MealsListContract.MealsListIntent.OnFetchMealsListData(
                    categoryType = TestDataGenerator.categoryType
                )
            )
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MealsListContract.MealsListState(
                    mealsListState = MealsListContract.MealsListDataState.Loading,
                    selectedMeal = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData =
                (expected.mealsListState as MealsListContract.MealsListDataState.Success).mealsList
            Truth.assertThat(expected).isEqualTo(
                MealsListContract.MealsListState(
                    mealsListState = MealsListContract.MealsListDataState.Success(meals),
                    selectedMeal = null
                )
            )
            Truth.assertThat(expectedData).containsExactlyElementsIn(meals)

            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // Then
        coVerify { getMealsListUseCase.invoke(TestDataGenerator.categoryType) }
    }

    @Test
    fun test_fetch_meals_list_fail() = runTest {
        val mealsErrorFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getMealsListUseCase.invoke(TestDataGenerator.categoryType) } returns mealsErrorFlow

        // When && Assertions (UiState)
        mealsListViewModel.uiState.test {
            // Call method inside of this scope
            mealsListViewModel.setIntent(
                MealsListContract.MealsListIntent.OnFetchMealsListData(
                    categoryType = TestDataGenerator.categoryType
                )
            )
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MealsListContract.MealsListState(
                    mealsListState = MealsListContract.MealsListDataState.Loading,
                    selectedMeal = null
                )
            )

            // Expect Resource.Success
            val expected = expectItem()
            val expectedData =
                (expected.mealsListState as MealsListContract.MealsListDataState.Error).errorMsg
            Truth.assertThat(expected).isEqualTo(
                MealsListContract.MealsListState(
                    mealsListState = MealsListContract.MealsListDataState.Error("error"),
                    selectedMeal = null
                )
            )
            Truth.assertThat(expectedData).isEqualTo("error")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then
        coVerify { getMealsListUseCase.invoke(TestDataGenerator.categoryType) }
    }

    @Test
    fun test_select_meal() = runTest {
        val mealItem = TestDataGenerator.generateMealItem(TestDataGenerator.mealId1)

        // Given (no-op)

        // When && Assertions
        mealsListViewModel.uiState.test {
            // Call method inside of this scope
            mealsListViewModel.setIntent(MealsListContract.MealsListIntent.OnMealClicked(meal = mealItem))
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MealsListContract.MealsListState(
                    mealsListState = MealsListContract.MealsListDataState.Loading,
                    selectedMeal = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = expected.selectedMeal

            Truth.assertThat(expected).isEqualTo(
                MealsListContract.MealsListState(
                    mealsListState = MealsListContract.MealsListDataState.Loading,
                    selectedMeal = mealItem
                )
            )
            Truth.assertThat(expectedData).isEqualTo(mealItem)
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then (no-op)
    }
}