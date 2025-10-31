package com.store.ecommerceapplication.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryItem(
    val slug: String,
    val name: String,
    val url: String
)
