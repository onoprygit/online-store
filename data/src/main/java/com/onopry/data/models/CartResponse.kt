package com.onopry.data.models

import com.onopry.domain.models.cart.Cart
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    val basket: List<CartItemResponse>,
    val delivery: String,
    val id: String,
    val total: Int
){
    fun toDomain() = Cart(
        basket = basket.map { it.toDomain() },
        delivery =  delivery,
        id = id,
        total = total
    )
}