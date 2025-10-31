package com.store.ecommerceapplication.domain.repository

import com.store.ecommerceapplication.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getWishlistProducts(): Flow<List<Product>>
    suspend fun addToWishlist(product: Product)
    suspend fun removeFromWishlist(productId: Int)
    suspend fun isInWishlist(productId: Int): Boolean
    suspend fun clearWishlist()
}
