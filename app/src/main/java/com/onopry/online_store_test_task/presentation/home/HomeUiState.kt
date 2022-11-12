package com.onopry.online_store_test_task.presentation.home

import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.online_store_test_task.presentation.base.BaseUiState

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val data: BannerAndProduct? = null,
//    val banners: List<ProductBanner> = emptyList(),
//    val products: List<Product> = emptyList(),
    override val errorMessage: String = ""
): BaseUiState<BannerAndProduct>()
