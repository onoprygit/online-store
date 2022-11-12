package com.onopry.domain.usecases

import com.onopry.domain.repository.StoreRepository

class GetProductDetailsUseCase(
    private val repository: StoreRepository
) {
    suspend operator fun invoke() = repository.getProductDetails()
}