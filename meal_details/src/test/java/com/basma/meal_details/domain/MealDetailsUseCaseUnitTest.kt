package com.basma.meal_details.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.meal_details.domain.contract.MealDetailsRepositoryContract
import com.basma.meal_details.domain.usecase.GetMealDetailsUseCase
import com.basma.meal_details.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MealDetailsUseCaseUnitTest {
    @MockK
    private lateinit var repository: MealDetailsRepositoryContract

    private lateinit var getMealDetailsUseCase: GetMealDetailsUseCase


    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getMealDetailsUseCase = GetMealDetailsUseCase(
            repositoryContract = repository
        )
    }

    @Test
    fun test_get_meal_details_success() = runTest{
        val mealDetails = TestDataGenerator.generateMealDetailsItem(TestDataGenerator.mealId1)
        val mealsFlow = flowOf(Resource.Success(mealDetails))

        // Given
        coEvery { repository.getMealDetails(TestDataGenerator.mealId1.toString()) } returns mealsFlow

        // When & Assertions
        val result = getMealDetailsUseCase.invoke(TestDataGenerator.mealId1.toString())
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(mealsFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getMealDetails(TestDataGenerator.mealId1.toString()) }
    }

    @Test
    fun test_get_meal_details_fail() = runTest{
        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getMealDetails(TestDataGenerator.mealId1.toString()) } returns errorFlow

        // When & Assertions
        val result = getMealDetailsUseCase.invoke(TestDataGenerator.mealId1.toString())
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getMealDetails(TestDataGenerator.mealId1.toString()) }
    }

}