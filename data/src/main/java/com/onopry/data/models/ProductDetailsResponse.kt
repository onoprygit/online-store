package com.onopry.data.models

import com.onopry.domain.models.details.ProductDetails
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailsResponse(
    val CPU: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val id: String,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
) {
    fun toDomain() = ProductDetails(
        CPU = CPU,
        camera = camera,
        capacity = capacity,
        color = color,
        id = id,
        images = images,
        isFavorites = isFavorites,
        price = price.toString(),
        rating = rating,
        sd = sd,
        ssd = ssd,
        title = title
    )
}