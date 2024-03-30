package com.basma.homepage.data.repo

import com.basma.common.utils.Mapper
import com.basma.common.utils.Resource
import com.basma.homepage.data.local.HomePageLocalDataSourceContract
import com.basma.homepage.data.local.HomePageLocalEntity
import com.basma.homepage.data.remote.HomePageRemoteDataSourceContract
import com.basma.homepage.domain.contract.HomePageRepositoryContract
import com.basma.homepage.domain.entity.HomePageDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomePageRepositoryImp @Inject constructor(
    private val localDataSource: HomePageLocalDataSourceContract,
    private val remoteDataSource: HomePageRemoteDataSourceContract,
    private val homePageDataMapper: Mapper<HomePageDataModel, HomePageLocalEntity>,
) : HomePageRepositoryContract {
    override suspend fun getHomePageData(): Flow<Resource<HomePageDataModel>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val response = remoteDataSource.getHomePageData()
                emit(Resource.Success(response))
                // Save to local
                localDataSource.insertHomePageDataList(homePageDataMapper.from(response))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails, get data from LocalDataSource
                try {
                    val localData = localDataSource.getHomePageDataFromDataBase()
                    emit(Resource.Success(homePageDataMapper.to(localData)))

                } catch (ex1: Exception) {
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}