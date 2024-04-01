package com.basma.meal_details.data

import androidx.test.filters.SmallTest
import com.basma.meal_details.data.remote.MealDetailsRemoteDataSourceImp
import com.basma.meal_details.data.remote.MealDetailsRemoteServices
import com.basma.meal_details.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
@SmallTest
class MealDetailsRemoteDataSourceImpUnitTest {

    @MockK
    private lateinit var remoteServices: MealDetailsRemoteServices

    private lateinit var remoteDataSource: MealDetailsRemoteDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = MealDetailsRemoteDataSourceImp(
            remoteServices = remoteServices
        )
    }

    @Test
    fun test_get_meal_details_success() = runTest {
        val mealDetailsResponse = TestDataGenerator.generateListOfMealDetailsItem()

        // Given
        coEvery { remoteServices.getMealDetails(TestDataGenerator.mealId1.toString()) } returns mealDetailsResponse

        // When
        val result = remoteDataSource.getMealDetails(TestDataGenerator.mealId1.toString())

        // Then
        coVerify { remoteServices.getMealDetails(TestDataGenerator.mealId1.toString()) }

        // Assertion
        val expected = mealDetailsResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_meal_details_fail() = runTest {
        // Given
        coEvery { remoteServices.getMealDetails(TestDataGenerator.mealId1.toString()) } throws Exception()

        // When
        remoteDataSource.getMealDetails(TestDataGenerator.mealId1.toString())

        // Then
        coVerify { remoteServices.getMealDetails(TestDataGenerator.mealId1.toString()) }
    }
}