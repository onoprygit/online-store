package com.onopry.domain.models.home

data class Product(
    val discountPrice: String,
    val id: Int,
    val isFavorites: Boolean,
    val picture: String,
    val priceWithoutDiscount: String,
    val title: String
)