package com.basma.homepage.data

import androidx.test.filters.SmallTest
import com.basma.homepage.data.remote.HomePageRemoteDataSourceImp
import com.basma.homepage.data.remote.HomePageRemoteServices
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
import java.lang.Exception

@ExperimentalCoroutinesApi
@SmallTest
class HomePageRemoteDataSourceImpUnitTest {
    @MockK
    private lateinit var remoteServices: HomePageRemoteServices

    private lateinit var remoteDataSource: HomePageRemoteDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = HomePageRemoteDataSourceImp(
            remoteServices = remoteServices
        )
    }

    @Test
    fun test_get_homepage_data_success() = runTest   {
        val homePageResponse = TestDataGenerator.generateHomePageData()

        // Given
        coEvery { remoteServices.getHomePageData() } returns homePageResponse

        // When
        val result = remoteDataSource.getHomePageData()

        // Then
        coVerify { remoteServices.getHomePageData() }

        // Assertion
        val expected = homePageResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_homepage_data_fail() = runTest {
        // Given
        coEvery { remoteServices.getHomePageData() } throws Exception()

        // When
        remoteDataSource.getHomePageData()

        // Then
        coVerify { remoteServices.getHomePageData() }
    }
}