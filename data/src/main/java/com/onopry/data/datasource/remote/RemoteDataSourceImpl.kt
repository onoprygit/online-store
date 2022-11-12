package com.onopry.data.datasource.remote

import com.onopry.data.network.StoreApi
import com.onopry.data.utils.safeApiCall

class RemoteDataSourceImpl(private val api: StoreApi) : RemoteDataSource {
    override suspend fun getBannersAndProducts() = safeApiCall { api.getBannersAndProducts() }
    override suspend fun getProductDetails() = safeApiCall { api.getDetails() }
}