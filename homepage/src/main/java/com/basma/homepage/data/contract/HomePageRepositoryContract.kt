package com.basma.homepage.data.contract

import com.basma.common.utils.Resource
import com.basma.homepage.data.entity.Data
import kotlinx.coroutines.flow.Flow

interface HomePageRepositoryContract {
    suspend fun getHomePageData(): Flow<Resource<Data>>
}