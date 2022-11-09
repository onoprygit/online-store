package com.onopry.domain.usecases

import com.onopry.domain.repository.StoreRepository

class GetProductCategoriesUseCase(
    private val repository: StoreRepository
) {
    operator fun invoke() = repository.getProductCategories()
}