package com.basma.meals_list.data

import androidx.test.filters.SmallTest
import com.basma.meals_list.data.remote.MealsListRemoteDataSourceImp
import com.basma.meals_list.data.remote.MealsListRemoteServices
import com.basma.meals_list.utils.TestDataGenerator
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
class MealsListRemoteDataSourceImpUnitTest {
    @MockK
    private lateinit var remoteServices: MealsListRemoteServices

    private lateinit var remoteDataSource: MealsListRemoteDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = MealsListRemoteDataSourceImp(
            remoteServices = remoteServices
        )
    }

    @Test
    fun test_get_meals_success() = runTest {
        val mealsResponse = TestDataGenerator.generateListOfRemoteMealItem()

        // Given
        coEvery { remoteServices.getMealsList(TestDataGenerator.categoryType) } returns mealsResponse

        // When
        val result = remoteDataSource.getMealsList(TestDataGenerator.categoryType)

        // Then
        coVerify { remoteServices.getMealsList(TestDataGenerator.categoryType) }

        // Assertion
        val expected = mealsResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_meals_fail() = runTest {
        // Given
        coEvery { remoteServices.getMealsList(TestDataGenerator.categoryType) } throws Exception()

        // When
        remoteDataSource.getMealsList(TestDataGenerator.categoryType)

        // Then
        coVerify { remoteServices.getMealsList(TestDataGenerator.categoryType) }
    }
}