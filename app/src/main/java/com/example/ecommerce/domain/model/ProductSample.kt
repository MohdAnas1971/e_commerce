package com.example.ecommerce.domain.model

data class ProductSample(
    val name: String,
    val description: String,
    val price: String,
    val review: Review,
    val imageRes: Int,
)



data class Review(
    val reviewNo: Long,
    val reviewStar: Int,
)



