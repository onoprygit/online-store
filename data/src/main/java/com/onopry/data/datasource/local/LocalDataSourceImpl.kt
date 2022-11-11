package com.onopry.data.datasource.local

import com.onopry.data.models.ProductCategoryEntity

class LocalDataSourceImpl : LocalDataSource {
    override fun getCategories() = listOf(
        ProductCategoryEntity(id = 10001, name = "Phones"),
        ProductCategoryEntity(id = 10002, name = "Computer"),
        ProductCategoryEntity(id = 10003, name = "Health"),
        ProductCategoryEntity(id = 10004, name = "Books"),
        ProductCategoryEntity(id = 10005, name = "Software"),
    )
}