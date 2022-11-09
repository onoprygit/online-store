package com.onopry.data.models

import com.onopry.domain.models.home.ProductBanner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeStore(
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_buy")
    val isBuy: Boolean,
    @Json(name = "is_new")
    val isNew: Boolean,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "subtitle")
    val subtitle: String,
    @Json(name = "title")
    val title: String
) {
    fun toDomain() = ProductBanner(id, isBuy, isNew, picture, subtitle, title)
}