package com.onopry.data.models

import com.onopry.domain.models.details.ItemDetails

data class DetailsResponse(
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
    fun toDomain() = ItemDetails(
        CPU = CPU,
        camera = camera,
        capacity = capacity,
        color = color,
        id = id,
        images = images,
        isFavorites = isFavorites,
        price = price,
        rating = rating,
        sd = sd,
        ssd = ssd,
        title = title
    )
}