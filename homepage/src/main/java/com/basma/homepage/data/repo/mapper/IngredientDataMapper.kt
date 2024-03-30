package com.basma.homepage.data.repo.mapper

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.entity.IngredientLocalEntity
import com.basma.homepage.domain.entity.Ingredient
import javax.inject.Inject

class IngredientDataMapper @Inject constructor(): Mapper<Ingredient, IngredientLocalEntity> {

    override fun from(i: Ingredient?): IngredientLocalEntity {
        return IngredientLocalEntity(
            idIngredient = i?.idIngredient ?: "",
            strDescription = i?.strDescription ?: "",
            strIngredient = i?.strIngredient ?: "",
            strType = i?.strType ?: ""
        )
    }

    override fun to(o: IngredientLocalEntity?): Ingredient {
        return Ingredient(
            idIngredient = o?.idIngredient ?: "",
            strDescription = o?.strDescription ?: "",
            strIngredient = o?.strIngredient ?: "",
            strType = o?.strType ?: ""
        )
    }
}