package com.onopry.data.repository

import com.onopry.data.datasource.local.LocalDataSource
import com.onopry.data.datasource.remote.RemoteDataSource
import com.onopry.data.models.CartResponse
import com.onopry.domain.models.cart.Cart
import com.onopry.domain.models.details.ProductDetails
import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.models.home.ProductCategory
import com.onopry.domain.repository.StoreRepository
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiResult
import com.onopry.domain.utils.ApiSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StoreRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : StoreRepository {

//    private val cartFlow: Flow<ApiResult<CartResponse>> = flow {
//        emit(remoteDataSource.getCart())
//    }

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
        when (val response = remoteDataSource.getProductDetails()) {
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

    override suspend fun getUserCart(): ApiResult<Cart> =
        when (val response = remoteDataSource.getCart()) {
            is ApiSuccess -> ApiSuccess<Cart>(data = response.data.toDomain())
            is ApiError -> ApiError<Cart>(
                code = response.code,
                message = response.message
            )
            is ApiException -> ApiException<Cart>(exception = response.exception)
        }
}