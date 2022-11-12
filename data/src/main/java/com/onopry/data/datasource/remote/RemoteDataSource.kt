package com.onopry.data.datasource.remote

import com.onopry.data.models.CartResponse
import com.onopry.domain.utils.ApiResult
import com.onopry.data.models.HomeResponse
import com.onopry.data.models.ProductDetailsResponse
import com.onopry.domain.models.cart.Cart

interface RemoteDataSource {
    suspend fun getBannersAndProducts(): ApiResult<HomeResponse>
    suspend fun getProductDetails(): ApiResult<ProductDetailsResponse>
    suspend fun getCart(): ApiResult<CartResponse>
}

