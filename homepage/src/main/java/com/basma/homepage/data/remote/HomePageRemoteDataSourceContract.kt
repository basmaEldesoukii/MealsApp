package com.basma.homepage.data.remote

import com.basma.homepage.domain.entity.HomePageDataModel

interface HomePageRemoteDataSourceContract {
    suspend fun getHomePageData(): HomePageDataModel
}