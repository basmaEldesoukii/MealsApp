package com.basma.homepage.data

import androidx.test.filters.SmallTest
import com.basma.homepage.data.local.HomePageDao
import com.basma.homepage.data.local.HomePageLocalDataSourceImp
import com.basma.homepage.utils.TestDataGenerator
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
class HomePageLocalDataSourceImpUnitTest {
    @MockK
    private lateinit var homePageDao: HomePageDao

    private lateinit var localDataSourceImp: HomePageLocalDataSourceImp

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        localDataSourceImp = HomePageLocalDataSourceImp(
            homePageDao = homePageDao
        )
    }

    @Test
    fun test_get_homepage_data_success() = runTest {
        val localData = TestDataGenerator.generateHomePageLocalData()

        // Given
        coEvery { homePageDao.getHomePageData() } returns localData

        // When
        val returned = localDataSourceImp.getHomePageDataFromDataBase()

        // Then
        coVerify { homePageDao.getHomePageData() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(localData)
    }

    @Test(expected = Exception::class)
    fun test_get_homepage_data_fail() = runTest {
        // Given
        coEvery { homePageDao.getHomePageData() } throws Exception()

        // When
        localDataSourceImp.getHomePageDataFromDataBase()

        // Then
        coVerify { homePageDao.getHomePageData() }
    }

    @Test
    fun test_insert_homepage_data_success() = runTest {
        val localData = TestDataGenerator.generateHomePageLocalData()
        val expected = 1L

        // Given
        coEvery { homePageDao.insertHomePageData(localData) } returns expected

        // When
        val returned = localDataSourceImp.insertHomePageDataList(localData)

        // Then
        coVerify { homePageDao.insertHomePageData(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_insert_homepage_data_fail() = runTest {
        val localData = TestDataGenerator.generateHomePageLocalData()

        // Given
        coEvery { homePageDao.getHomePageData() } throws Exception()

        // When
        localDataSourceImp.insertHomePageDataList(localData)

        // Then
        coVerify { homePageDao.insertHomePageData(any()) }
    }


    @Test
    fun test_clear_all_homepage_data_success() = runTest {
        val localData = TestDataGenerator.generateHomePageLocalData()
        val expected = localData.id // Affected row count

        // Given
        coEvery { homePageDao.clearHomePageDataCash() } returns expected.toInt()

        // When
        val returned = localDataSourceImp.clearHomePageDataCashed()

        // Then
        coVerify { homePageDao.clearHomePageDataCash() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_clear_all_homepage_data_fail() = runTest {
        // Given
        coEvery { homePageDao.clearHomePageDataCash() } throws Exception()

        // When
        localDataSourceImp.clearHomePageDataCashed()

        // Then
        coVerify { homePageDao.clearHomePageDataCash() }
    }
}