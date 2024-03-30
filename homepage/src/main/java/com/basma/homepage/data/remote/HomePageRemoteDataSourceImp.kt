package com.basma.homepage.data.remote

import com.basma.homepage.domain.entity.HomePageDataModel
import javax.inject.Inject

class HomePageRemoteDataSourceImp @Inject constructor(
    private val apiServices: HomePageRemoteServices
): HomePageRemoteDataSourceContract {
    override suspend fun getHomePageData(): HomePageDataModel {
        return apiServices.getHomePageData()
    }
}