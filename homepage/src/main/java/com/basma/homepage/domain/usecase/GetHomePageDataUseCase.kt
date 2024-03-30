package com.basma.homepage.domain.usecase

import com.basma.homepage.domain.contract.HomePageRepositoryContract

class GetHomePageDataUseCase(
    private val repositoryContract: HomePageRepositoryContract
) {
    suspend operator fun invoke() = repositoryContract.getHomePageData()
}