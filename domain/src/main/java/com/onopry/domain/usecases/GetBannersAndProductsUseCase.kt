package com.onopry.domain.usecases

import com.onopry.domain.repository.StoreRepository

class GetBannersAndProductsUseCase(
    private val repository: StoreRepository
) {
    suspend operator fun invoke() = repository.getBannersAndProducts()
}