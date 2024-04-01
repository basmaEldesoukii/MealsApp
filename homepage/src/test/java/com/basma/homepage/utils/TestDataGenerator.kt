package com.basma.homepage.utils

import com.basma.homepage.domain.entity.Category
import com.basma.homepage.domain.entity.Data
import com.basma.homepage.domain.entity.DynamicCollectionViewModel
import com.basma.homepage.domain.entity.HomePageDataModel
import com.basma.homepage.domain.entity.HomePageDataType

class TestDataGenerator {
    companion object{
        // Data for UseCase Test
        fun generateHomePageData(): HomePageDataModel {
            return generateDataItem()
        }

        fun generateDataItem(): HomePageDataModel {
            return HomePageDataModel(
                Data = Data(
                    DynamicCollectionViewModel = listOf(
                        DynamicCollectionViewModel(
                            Type = HomePageDataType.Category,
                            Title = "Top Categories",
                            Order = 3,
                            Categories = listOf(
                                Category(
                                    idCategory = "1",
                                    strCategory = "Mock Beef",
                                    strCategoryThumb = "https://www.example.com/beef.png",
                                    strCategoryDescription = "Mock description for beef"
                                )
                            ),
                            Announcements = listOf(),
                            Ingredients = listOf(),
                            Id = 1,
                            Meals = listOf(),
                            Url = "https://www.example.com"
                        )
                    )
                )
            )
        }
        //endOfRegion


    }
}