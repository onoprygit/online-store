package com.onopry.domain.usecases

import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.repository.StoreRepository
import com.onopry.domain.utils.ApiResult

class GetBannersAndProductsUseCase(
    private val repository: StoreRepository
){
    suspend operator fun invoke() = repository.getBannersAndProducts()
}