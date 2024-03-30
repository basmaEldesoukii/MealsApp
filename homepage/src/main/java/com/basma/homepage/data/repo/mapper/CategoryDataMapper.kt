package com.basma.homepage.data.repo.mapper

import com.basma.common.utils.Mapper
import com.basma.homepage.data.local.entity.CategoryLocalEntity
import com.basma.homepage.domain.entity.Category
import javax.inject.Inject

class CategoryDataMapper @Inject constructor() : Mapper<Category, CategoryLocalEntity> {

    override fun from(i: Category?): CategoryLocalEntity {
        return CategoryLocalEntity(
            idCategory = i?.idCategory ?: "",
            strCategory = i?.strCategory ?: "",
            strCategoryThumb = i?.strCategoryThumb ?: "",
            strCategoryDescription = i?.strCategoryDescription ?: ""
        )
    }

    override fun to(o: CategoryLocalEntity?): Category {
        return Category(
            idCategory = o?.idCategory ?: "",
            strCategory = o?.strCategory ?: "",
            strCategoryThumb = o?.strCategoryThumb ?: "",
            strCategoryDescription = o?.strCategoryDescription ?: ""
        )
    }
}
