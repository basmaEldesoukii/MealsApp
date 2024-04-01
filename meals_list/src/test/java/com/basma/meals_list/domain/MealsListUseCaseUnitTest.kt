package com.basma.meals_list.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.meals_list.domain.contract.MealsListRepositoryContract
import com.basma.meals_list.domain.usecase.GetMealsListUseCase
import com.basma.meals_list.utils.TestDataGenerator
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
class MealsListUseCaseUnitTest {
    @MockK
    private lateinit var repository: MealsListRepositoryContract

    private lateinit var getMealsListUseCase: GetMealsListUseCase


    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getMealsListUseCase = GetMealsListUseCase(
            repositoryContract = repository,
        )
    }

    @Test
    fun test_get_meals_list_success() = runTest{
        val meals = TestDataGenerator.generateMealsList()
        val mealsFlow = flowOf(Resource.Success(meals))

        // Given
        coEvery { repository.getMealsList(TestDataGenerator.categoryType) } returns mealsFlow

        // When & Assertions
        val result = getMealsListUseCase.invoke(TestDataGenerator.categoryType)
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(mealsFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getMealsList(TestDataGenerator.categoryType) }
    }

    @Test
    fun test_get_meals_list_fail() = runTest{

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getMealsList(TestDataGenerator.categoryType) } returns errorFlow

        // When & Assertions
        val result = getMealsListUseCase.invoke(TestDataGenerator.categoryType)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getMealsList(TestDataGenerator.categoryType) }
    }
}