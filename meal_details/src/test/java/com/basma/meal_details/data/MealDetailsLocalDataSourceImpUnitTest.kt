package com.basma.meal_details.data

import androidx.test.filters.SmallTest
import com.basma.meal_details.data.local.MealDetailsDao
import com.basma.meal_details.data.local.MealDetailsLocalDataSourceImp
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

@ExperimentalCoroutinesApi
@SmallTest
class MealDetailsLocalDataSourceImpUnitTest {
    @MockK
    private lateinit var mealDetailsDao: MealDetailsDao

    private lateinit var localDataSourceImp: MealDetailsLocalDataSourceImp

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        localDataSourceImp = MealDetailsLocalDataSourceImp(
            mealDetailsDao = mealDetailsDao
        )
    }

    @Test
    fun test_get_meal_details_success() = runTest {
        val expectedMealDetails = TestDataGenerator.generateMealDetailsLocalData()

        // Given
        coEvery { mealDetailsDao.getMealDetails(any()) } returns expectedMealDetails

        // When
        val returned = localDataSourceImp.getMealDetailsFromDataBase(expectedMealDetails.idMeal)

        // Then
        coVerify { mealDetailsDao.getMealDetails(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expectedMealDetails)
    }

    @Test(expected = Exception::class)
    fun test_get_meal_details_fail() = runTest {
        val mealDetails = TestDataGenerator.generateMealDetailsLocalData()

        // Given
        coEvery { mealDetailsDao.getMealDetails(any()) } throws Exception()

        // When
        localDataSourceImp.getMealDetailsFromDataBase(mealDetails.idMeal)

        // Then
        coVerify { mealDetailsDao.getMealDetails(any()) }
    }


    @Test
    fun test_insert_meal_details_success() = runTest {
        val localMeals = TestDataGenerator.generateMealDetailsLocalData()
        val expected = 1L

        // Given
        coEvery { mealDetailsDao.insertMealDetails(any()) } returns expected

        // When
        val returned = localDataSourceImp.insertMealDetails(localMeals)

        // Then
        coVerify { mealDetailsDao.insertMealDetails(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_insert_meal_details_fail() = runTest {
        val localMeals = TestDataGenerator.generateMealDetailsLocalData()

        // Given
        coEvery { mealDetailsDao.insertMealDetails(any()) } throws Exception()

        // When
        localDataSourceImp.insertMealDetails(localMeals)

        // Then
        coVerify { mealDetailsDao.insertMealDetails(any()) }
    }

    @Test
    fun test_clear_all_meal_details_success() = runTest {
        val localMeals = TestDataGenerator.generateListOfMealDetailsItem()
        val expected = localMeals.mealDetails.size // Affected row count

        // Given
        coEvery { mealDetailsDao.clearMealDetailsCash() } returns expected

        // When
        val returned = localDataSourceImp.clearMealDetailsCashed()

        // Then
        coVerify { mealDetailsDao.clearMealDetailsCash() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_clear_all_meal_details_fail() = runTest {
        // Given
        coEvery { mealDetailsDao.clearMealDetailsCash() } throws Exception()

        // When
        localDataSourceImp.clearMealDetailsCashed()

        // Then
        coVerify { mealDetailsDao.clearMealDetailsCash() }
    }
}