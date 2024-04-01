package com.basma.homepage.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.homepage.data.local.HomePageLocalDataSourceContract
import com.basma.homepage.data.remote.HomePageRemoteDataSourceContract
import com.basma.homepage.data.repo.HomePageDataMapper
import com.basma.homepage.data.repo.HomePageRepositoryImp
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
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class HomePageRepositoryImpUnitTest {
    @MockK
    private lateinit var localDataSourceContract: HomePageLocalDataSourceContract

    @MockK
    private lateinit var remoteDataSourceContract: HomePageRemoteDataSourceContract

    private val homePageDataMapper = HomePageDataMapper()

    private lateinit var repository: HomePageRepositoryImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = HomePageRepositoryImp(
            localDataSource = localDataSourceContract,
            remoteDataSource = remoteDataSourceContract,
            homePageDataMapper = homePageDataMapper
        )
    }

    @Test
    fun test_get_homepage_data_from_remote_success() = runTest{
        val homepageData = TestDataGenerator.generateHomePageData()
        val affectedIds = 1L

        // Given
        coEvery { remoteDataSourceContract.getHomePageData() } returns homepageData
        coEvery { localDataSourceContract.insertHomePageDataList(homePageDataMapper.from(homepageData)) } returns affectedIds
        coEvery { localDataSourceContract.getHomePageDataFromDataBase() } returns homePageDataMapper.from(homepageData)

        // When & Assertions
        val flow = repository.getHomePageData()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData.Data).isEqualTo(homepageData.Data)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getHomePageData() }
        coVerify { localDataSourceContract.insertHomePageDataList(homePageDataMapper.from(homepageData)) }
    }

    @Test
    fun test_get_homepage_data_from_local_when_remote_fail() = runTest {
        val homepageData = TestDataGenerator.generateHomePageData()

        // Given
        coEvery { remoteDataSourceContract.getHomePageData() } throws Exception()
        coEvery { localDataSourceContract.getHomePageDataFromDataBase() } returns homePageDataMapper.from(homepageData)

        // When && Assertions
        val flow = repository.getHomePageData()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData.Data).isEqualTo(homepageData.Data)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getHomePageData() }
        coVerify { localDataSourceContract.getHomePageDataFromDataBase() }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_data() = runTest {
        // Given
        coEvery { remoteDataSourceContract.getHomePageData() } throws Exception()
        coEvery { localDataSourceContract.getHomePageDataFromDataBase() } throws Exception()

        // When && Assertions
        val flow = repository.getHomePageData()
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getHomePageData() }
        coVerify { localDataSourceContract.getHomePageDataFromDataBase() }
    }
}