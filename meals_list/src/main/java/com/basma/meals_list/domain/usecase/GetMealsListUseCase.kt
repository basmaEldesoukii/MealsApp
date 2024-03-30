package com.basma.meals_list.domain.usecase

import com.basma.meals_list.domain.contract.MealsListRepositoryContract

class GetMealsListUseCase(
    private val repositoryContract: MealsListRepositoryContract
) {
    suspend operator fun invoke(categoryType: String) = repositoryContract.getMealsList(categoryType)
}