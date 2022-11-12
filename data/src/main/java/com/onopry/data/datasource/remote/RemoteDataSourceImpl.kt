package com.onopry.data.datasource.remote

import com.onopry.data.models.CartResponse
import com.onopry.data.network.StoreApi
import com.onopry.data.utils.safeApiCall
import com.onopry.domain.models.cart.Cart
import com.onopry.domain.utils.ApiResult

class RemoteDataSourceImpl(private val api: StoreApi) : RemoteDataSource {
    override suspend fun getBannersAndProducts() = safeApiCall { api.getBannersAndProducts() }
    override suspend fun getProductDetails() = safeApiCall { api.getDetails() }
    override suspend fun getCart(): ApiResult<CartResponse> = safeApiCall { api.getCart() }
}