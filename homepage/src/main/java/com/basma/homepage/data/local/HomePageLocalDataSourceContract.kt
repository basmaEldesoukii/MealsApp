package com.basma.homepage.data.local

interface HomePageLocalDataSourceContract {
    suspend fun getHomePageDataFromDataBase(): HomePageLocalEntity
    suspend fun insertHomePageDataList(data: HomePageLocalEntity): Long
    suspend fun clearHomePageDataCashed(): Int
}