package com.basma.homepage.data.repo

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.HomePageLocalEntity
import com.basma.homepage.domain.entity.Data
import com.basma.homepage.domain.entity.HomePageDataModel
import javax.inject.Inject

class HomePageDataMapper @Inject constructor() : Mapper<HomePageDataModel, HomePageLocalEntity> {
    override fun from(i: HomePageDataModel?): HomePageLocalEntity {
        return HomePageLocalEntity(
            data = i?.Data ?: Data(
                DynamicCollectionViewModel = emptyList()
            )
        )
    }

    override fun to(o: HomePageLocalEntity?): HomePageDataModel {
        return HomePageDataModel(
            Data = o?.data ?: Data(
                DynamicCollectionViewModel = emptyList()
            )
        )
    }
}