package com.onopry.data.datasource.remote

import com.onopry.domain.utils.ApiResult
import com.onopry.data.models.HomeResponse
import com.onopry.data.models.ProductDetailsResponse

interface RemoteDataSource {
    suspend fun getBannersAndProducts(): ApiResult<HomeResponse>
    suspend fun getProductDetails(): ApiResult<ProductDetailsResponse>
}

