package com.basma.meal_details.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.meal_details.data.local.MealDetailsLocalDataSourceContract
import com.basma.meal_details.data.remote.MealDetailsRemoteDataSourceContract
import com.basma.meal_details.data.repo.MealDetailsDataMapper
import com.basma.meal_details.data.repo.MealDetailsRepositoryImp
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
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class MealDetailsRepositoryImpUnitTest {

    @MockK
    private lateinit var localDataSourceContract: MealDetailsLocalDataSourceContract

    @MockK
    private lateinit var remoteDataSourceContract: MealDetailsRemoteDataSourceContract

    private val mealDetailsDataMapper = MealDetailsDataMapper()

    private lateinit var repository: MealDetailsRepositoryImp

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = MealDetailsRepositoryImp(
            localDataSource = localDataSourceContract,
            remoteDataSource = remoteDataSourceContract,
            mealDetailsDataMapper = mealDetailsDataMapper
        )
    }

    @Test
    fun test_get_meal_details_from_remote_success() = runTest {
        val mealDetailsRemote = TestDataGenerator.generateListOfMealDetailsItem()
        val affectedIds = 1L

        // Given
        coEvery { remoteDataSourceContract.getMealDetails(TestDataGenerator.mealId1.toString()) } returns mealDetailsRemote
        coEvery {
            localDataSourceContract.insertMealDetails(
                mealDetailsDataMapper.from(
                    mealDetailsRemote.mealDetails[0]
                )
            )
        } returns affectedIds
        coEvery { localDataSourceContract.getMealDetailsFromDataBase(TestDataGenerator.mealId1.toString()) } returns mealDetailsDataMapper.from(
            mealDetailsRemote.mealDetails[0]
        )

        // When & Assertions
        val flow = repository.getMealDetails(TestDataGenerator.mealId1.toString())
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData.idMeal).isEqualTo(mealDetailsRemote.mealDetails[0])
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMealDetails(TestDataGenerator.mealId1.toString()) }
        coVerify {
            localDataSourceContract.insertMealDetails(
                mealDetailsDataMapper.from(
                    mealDetailsRemote.mealDetails[0]
                )
            )
        }
    }

    @Test
    fun test_get_meal_details_from_local_when_remote_fail() = runTest {
        val mealDetails = TestDataGenerator.generateMealDetailsItem(TestDataGenerator.mealId1)

        // Given
        coEvery { remoteDataSourceContract.getMealDetails(TestDataGenerator.mealId1.toString()) } throws Exception()
        coEvery { localDataSourceContract.getMealDetailsFromDataBase(TestDataGenerator.mealId1.toString()) } returns mealDetailsDataMapper.from(
            mealDetails
        )

        // When && Assertions
        val flow = repository.getMealDetails(TestDataGenerator.mealId1.toString())
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData.idMeal).isEqualTo(mealDetails.idMeal)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMealDetails(TestDataGenerator.mealId1.toString()) }
        coVerify { localDataSourceContract.getMealDetailsFromDataBase(TestDataGenerator.mealId1.toString()) }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_meals() = runTest {
        // Given
        coEvery { remoteDataSourceContract.getMealDetails(TestDataGenerator.mealId1.toString()) } throws Exception()
        coEvery { localDataSourceContract.getMealDetailsFromDataBase(TestDataGenerator.mealId1.toString()) } throws Exception()

        // When && Assertions
        val flow = repository.getMealDetails(TestDataGenerator.mealId1.toString())
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMealDetails(TestDataGenerator.mealId1.toString()) }
        coVerify { localDataSourceContract.getMealDetailsFromDataBase(TestDataGenerator.mealId1.toString()) }
    }
}