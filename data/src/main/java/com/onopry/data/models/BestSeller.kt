package com.onopry.data.models

import com.onopry.domain.models.home.Product
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BestSeller(
    @Json(name = "discount_price")
    val discountPrice: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_favorites")
    val isFavorites: Boolean,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "price_without_discount")
    val priceWithoutDiscount: Int,
    @Json(name = "title")
    val title: String
) {
    fun toDomain() = Product(
        discountPrice = "$$discountPrice",
        id = id,
        isFavorites = isFavorites,
        picture =  picture,
        priceWithoutDiscount = "$$priceWithoutDiscount",
        title = title
    )
}
