package com.onopry.data.models

import com.onopry.domain.models.home.BannerAndProduct
import com.onopry.domain.models.home.Product
import com.onopry.domain.models.home.ProductBanner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeResponse(
    @Json(name = "best_seller")
    val bestSeller: List<BestSeller>,
    @Json(name = "home_store")
    val homeStore: List<HomeStore>
) {
    fun toDomain() = BannerAndProduct(
        products = bestSeller.map { it.toDomain() },
        banners = homeStore.map { it.toDomain() }
    )
}