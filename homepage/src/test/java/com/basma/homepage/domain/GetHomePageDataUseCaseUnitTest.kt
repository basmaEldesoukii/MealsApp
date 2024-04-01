package com.basma.homepage.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.basma.common.utils.Resource
import com.basma.homepage.domain.contract.HomePageRepositoryContract
import com.basma.homepage.domain.usecase.GetHomePageDataUseCase
import com.basma.homepage.utils.TestDataGenerator
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
class GetHomePageDataUseCaseUnitTest {
    @MockK
    private lateinit var repository: HomePageRepositoryContract

    private lateinit var getHomePageDataUseCase: GetHomePageDataUseCase


    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getHomePageDataUseCase = GetHomePageDataUseCase(
            repositoryContract = repository,
        )
    }

    @Test
    fun test_get_homepage_data_success() = runTest {
        val data = TestDataGenerator.generateHomePageData()
        val dataFlow = flowOf(Resource.Success(data))

        // Given
        coEvery { repository.getHomePageData() } returns dataFlow

        // When & Assertions
        val result = getHomePageDataUseCase.invoke()
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(dataFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getHomePageData() }
    }

    @Test
    fun test_get_homepage_data_fail() = runTest{

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getHomePageData() } returns errorFlow

        // When & Assertions
        val result = getHomePageDataUseCase.invoke()
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getHomePageData() }
    }
}