package com.store.ecommerceapplication.data.repositoryimpl

import com.store.ecommerceapplication.data.local.dao.WishlistDao
import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow

class WishlistRepositoryImpl(
    private val wishlistDao: WishlistDao
) : WishlistRepository {
    
    override fun getWishlistProducts(): Flow<List<Product>> {
        return wishlistDao.getAllWishlistItems()
    }
    
    override suspend fun addToWishlist(product: Product) {
        wishlistDao.insertWishlistItem(product)
    }
    
    override suspend fun removeFromWishlist(productId: Int) {
        wishlistDao.deleteWishlistItem(productId)
    }
    
    override suspend fun isInWishlist(productId: Int): Boolean {
        return wishlistDao.isInWishlist(productId)
    }
    
    override suspend fun clearWishlist() {
        wishlistDao.clearWishlist()
    }
}
