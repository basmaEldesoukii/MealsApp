package com.basma.homepage.data.remote

import com.basma.homepage.domain.entity.HomePageDataModel
import retrofit2.http.GET

interface HomePageRemoteServices {
    @GET("home")
    suspend fun getHomePageData(): HomePageDataModel
}