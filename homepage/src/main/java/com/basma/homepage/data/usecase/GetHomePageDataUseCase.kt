package com.basma.homepage.data.usecase

import com.basma.homepage.data.contract.HomePageRepositoryContract

class GetHomePageDataUseCase(
    private val repositoryContract: HomePageRepositoryContract
) {
    suspend operator fun invoke() = repositoryContract.getHomePageData()
}