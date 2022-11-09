package com.onopry.domain.repository

import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.models.home.ProductCategory
import com.onopry.domain.utils.ApiResult

interface StoreRepository {
    suspend fun getBannersAndProducts(): ApiResult<BannerAndProduct>
    fun getProductCategories(): List<ProductCategory>
}