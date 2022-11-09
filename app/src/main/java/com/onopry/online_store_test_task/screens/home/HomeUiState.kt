package com.onopry.online_store_test_task.screens.home

import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.models.home.Product
import com.onopry.domain.models.home.ProductBanner

data class HomeUiState(
    val isLoading: Boolean = false,
    val banners: List<ProductBanner> = emptyList(),
    val products: List<Product> = emptyList(),
    val errorMessage: String = ""
)
