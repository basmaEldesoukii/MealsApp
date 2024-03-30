package com.basma.homepage.data.local

import javax.inject.Inject

class HomePageLocalDataSourceImp @Inject constructor(
    private val homePageDao: HomePageDao
) : HomePageLocalDataSourceContract {
    override suspend fun getHomePageDataFromDataBase(): HomePageLocalEntity {
        return homePageDao.getHomePageData()
    }

    override suspend fun insertHomePageDataList(data: HomePageLocalEntity): Long {
        return homePageDao.insertHomePageData(data)
    }

    override suspend fun clearHomePageDataCashed(): Int {
        return homePageDao.clearHomePageDataCash()
    }
}