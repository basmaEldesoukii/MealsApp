package com.basma.meals_list.data

import androidx.test.filters.SmallTest
import com.basma.meals_list.data.local.MealsListDao
import com.basma.meals_list.data.local.MealsListLocalDataSourceImp
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

@ExperimentalCoroutinesApi
@SmallTest
class MealsListLocalDataSourceImpUnitTest {
    @MockK
    private lateinit var mealsListDao: MealsListDao

    private lateinit var localDataSourceImp: MealsListLocalDataSourceImp

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        localDataSourceImp = MealsListLocalDataSourceImp(
            mealsListDao = mealsListDao
        )
    }

    @Test
    fun test_get_meals_success() = runTest {
        val expected = TestDataGenerator.generateListOfLocalMealItem()

        // Given
        coEvery { mealsListDao.getMealsList() } returns expected

        // When
        val returned = localDataSourceImp.getMealsListFromDataBase()

        // Then
        coVerify { mealsListDao.getMealsList() }

        // Assertion
        Truth.assertThat(returned).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_meals_fail() = runTest {
        // Given
        coEvery { mealsListDao.getMealsList() } throws Exception()

        // When
        localDataSourceImp.getMealsListFromDataBase()

        // Then
        coVerify { mealsListDao.getMealsList() }
    }

    @Test
    fun test_insert_meals_success() = runTest {
        val localMeals = TestDataGenerator.generateListOfLocalMealItem()
        val expected = MutableList(localMeals.size) { index -> index.toLong() }

        // Given
        coEvery { mealsListDao.insertMealsList(any()) } returns expected

        // When
        val returned = localDataSourceImp.insertMealsList(localMeals)

        // Then
        coVerify { mealsListDao.insertMealsList(any()) }

        // Assertion
        Truth.assertThat(returned).hasSize(expected.size)
    }

    @Test(expected = Exception::class)
    fun test_insert_meals_fail() = runTest {
        val localMeals = TestDataGenerator.generateListOfLocalMealItem()

        // Given
        coEvery { mealsListDao.insertMealsList(any()) } throws Exception()

        // When
        localDataSourceImp.insertMealsList(localMeals)

        // Then
        coVerify { mealsListDao.insertMealsList(any()) }
    }

    @Test
    fun test_clear_all_meals_success() = runTest {
        val localMeals = TestDataGenerator.generateListOfLocalMealItem()
        val expected = localMeals.size // Affected row count

        // Given
        coEvery { mealsListDao.clearMealsListCash() } returns expected

        // When
        val returned = localDataSourceImp.clearMealsListCashed()

        // Then
        coVerify { mealsListDao.clearMealsListCash() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_clear_all_meals_fail() = runTest {
        // Given
        coEvery { mealsListDao.clearMealsListCash() } throws Exception()

        // When
        localDataSourceImp.clearMealsListCashed()

        // Then
        coVerify { mealsListDao.clearMealsListCash() }
    }
}