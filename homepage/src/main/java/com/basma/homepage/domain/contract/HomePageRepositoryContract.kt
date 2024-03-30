package com.basma.homepage.domain.contract

import com.basma.common.utils.Resource
import com.basma.homepage.domain.entity.HomePageDataModel
import kotlinx.coroutines.flow.Flow

interface HomePageRepositoryContract {
    suspend fun getHomePageData(): Flow<Resource<HomePageDataModel>>
}