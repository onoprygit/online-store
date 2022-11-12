package com.onopry.data.repository

import com.onopry.data.datasource.local.LocalDataSource
import com.onopry.data.datasource.remote.RemoteDataSource
import com.onopry.domain.models.details.ProductDetails
import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.models.home.ProductCategory
import com.onopry.domain.repository.StoreRepository
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiResult
import com.onopry.domain.utils.ApiSuccess

class StoreRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : StoreRepository {
    override suspend fun getBannersAndProducts(): ApiResult<BannerAndProduct> =
        when (
            val response = remoteDataSource.getBannersAndProducts()
        ) {
            is ApiSuccess -> ApiSuccess<BannerAndProduct>(data = response.data.toDomain())
            is ApiError -> ApiError<BannerAndProduct>(
                code = response.code,
                message = response.message
            )
            is ApiException -> ApiException<BannerAndProduct>(exception = response.exception)
        }

    override suspend fun getProductDetails(): ApiResult<ProductDetails> =
        when (val response = remoteDataSource.getProductDetails()){
            is ApiSuccess -> ApiSuccess<ProductDetails>(data = response.data.toDomain())
            is ApiError -> ApiError<ProductDetails>(
                code = response.code,
                message = response.message
            )
            is ApiException -> ApiException<ProductDetails>(exception = response.exception)
        }

    override fun getProductCategories(): List<ProductCategory> =
        localDataSource.getCategories()
            .map { it.toDomain() }


}