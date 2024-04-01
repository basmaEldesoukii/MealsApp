package com.basma.meals_list.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.meals_list.data.local.MealsListLocalDataSourceContract
import com.basma.meals_list.data.remote.MealsListRemoteDataSourceContract
import com.basma.meals_list.data.repo.MealsListDataMapper
import com.basma.meals_list.data.repo.MealsListRepositoryImp
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
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class MealsListRepositoryImpUnitTest {

    @MockK
    private lateinit var localDataSourceContract: MealsListLocalDataSourceContract

    @MockK
    private lateinit var remoteDataSourceContract: MealsListRemoteDataSourceContract

    private val mealsListDataMapper = MealsListDataMapper()

    private lateinit var repository: MealsListRepositoryImp

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = MealsListRepositoryImp(
            localDataSource = localDataSourceContract,
            remoteDataSource = remoteDataSourceContract,
            mealsListDataMapper = mealsListDataMapper
        )
    }

    @Test
    fun test_get_meals_from_remote_success() = runTest {
        val meals = TestDataGenerator.generateMealsList()
        val remoteMeals = TestDataGenerator.generateListOfRemoteMealItem()
        val affectedIds = MutableList(meals.size) { index -> index.toLong() }

        // Given
        coEvery { remoteDataSourceContract.getMealsList(TestDataGenerator.categoryType) } returns remoteMeals
        coEvery { localDataSourceContract.insertMealsList(mealsListDataMapper.fromList(meals)) } returns affectedIds
        coEvery { localDataSourceContract.getMealsListFromDataBase() } returns mealsListDataMapper.fromList(
            meals
        )

        // When & Assertions
        val flow = repository.getMealsList(TestDataGenerator.categoryType)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].idMeal).isEqualTo(meals[0].idMeal)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMealsList(TestDataGenerator.categoryType) }
        coVerify { localDataSourceContract.insertMealsList(mealsListDataMapper.fromList(meals)) }
    }

    @Test
    fun test_get_meals_from_local_when_remote_fail() = runTest {
        val meals = TestDataGenerator.generateMealsList()

        // Given
        coEvery { remoteDataSourceContract.getMealsList(TestDataGenerator.categoryType) } throws Exception()
        coEvery { localDataSourceContract.getMealsListFromDataBase() } returns mealsListDataMapper.fromList(
            meals
        )

        // When && Assertions
        val flow = repository.getMealsList(TestDataGenerator.categoryType)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].idMeal).isEqualTo(meals[0].idMeal)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMealsList(TestDataGenerator.categoryType) }
        coVerify { localDataSourceContract.getMealsListFromDataBase() }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_meals() = runTest {
        // Given
        coEvery { remoteDataSourceContract.getMealsList(TestDataGenerator.categoryType) } throws Exception()
        coEvery { localDataSourceContract.getMealsListFromDataBase() } throws Exception()

        // When && Assertions
        val flow = repository.getMealsList(TestDataGenerator.categoryType)
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMealsList(TestDataGenerator.categoryType) }
        coVerify { localDataSourceContract.getMealsListFromDataBase() }
    }
}