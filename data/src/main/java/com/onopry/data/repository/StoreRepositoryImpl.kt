package com.onopry.data.repository

import com.onopry.data.datasource.local.LocalDataSource
import com.onopry.data.datasource.remote.RemoteDataSource
import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.repository.StoreRepository
import com.onopry.domain.utils.ApiError
import com.onopry.domain.utils.ApiException
import com.onopry.domain.utils.ApiSuccess

class StoreRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : StoreRepository {
    override suspend fun getBannersAndProducts() =
        when (val response = remoteDataSource.getBannersAndProducts()) {
            is ApiSuccess -> ApiSuccess<BannerAndProduct>(data = response.data.toDomain())
            is ApiError -> ApiError<BannerAndProduct>(
                code = response.code,
                message = response.message
            )
            is ApiException -> ApiException<BannerAndProduct>(exception = response.exception)
        }

    override fun getProductCategories() =
        localDataSource.getCategories()
            .map { it.toDomain() }
}