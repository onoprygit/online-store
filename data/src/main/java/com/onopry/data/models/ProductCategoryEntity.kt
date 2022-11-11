package com.onopry.data.models

import com.onopry.domain.models.home.ProductCategory

// Need if categories will be taken from outer data source(like room, or api service)
data class ProductCategoryEntity(
    val id: Int,
    val name: String,
) {
    fun toDomain() = ProductCategory(
        id = id,
        name = name
    )
}
