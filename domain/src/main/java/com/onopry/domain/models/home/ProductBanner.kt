package com.onopry.domain.models.home

data class ProductBanner(
    val id: Int,
    val isBuy: Boolean,
    val isNew: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
)