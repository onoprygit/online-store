package com.onopry.domain.models.cart

data class Cart(
    val basket: List<CartItem>,
    val delivery: String,
    val id: String,
    val total: Int
)