package com.onopry.data.datasource.local

import com.onopry.data.models.ProductCategoryEntity
import com.onopry.domain.models.home.ProductCategory

interface LocalDataSource {
    fun getCategories(): List<ProductCategoryEntity>
}