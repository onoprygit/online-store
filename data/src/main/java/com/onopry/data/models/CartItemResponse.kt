package com.onopry.data.models

import com.onopry.domain.models.cart.CartItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartItemResponse(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String
){
    fun toDomain() = CartItem(
        id, images, price, title
    )
}