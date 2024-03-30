package com.basma.meal_details.domain.usecase

import com.basma.meal_details.domain.contract.MealDetailsRepositoryContract

class GetMealDetailsUseCase(
    private val repositoryContract: MealDetailsRepositoryContract
) {
    suspend operator fun invoke() = repositoryContract.getMealDetails()
}