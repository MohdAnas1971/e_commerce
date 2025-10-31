package com.store.ecommerceapplication.data.local.dao

import androidx.room.*
import com.store.ecommerceapplication.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {
    
    @Query("SELECT * FROM wishlist")
    fun getAllWishlistItems(): Flow<List<Product>>
    
    @Query("SELECT * FROM wishlist WHERE id = :productId")
    suspend fun getWishlistItem(productId: Int): Product?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(item: Product)
    
    @Query("DELETE FROM wishlist WHERE id = :productId")
    suspend fun deleteWishlistItem(productId: Int)
    
    @Query("DELETE FROM wishlist")
    suspend fun clearWishlist()
    
    @Query("SELECT EXISTS(SELECT 1 FROM wishlist WHERE id = :productId)")
    suspend fun isInWishlist(productId: Int): Boolean
    
    @Query("SELECT COUNT(*) FROM wishlist")
    suspend fun getWishlistCount(): Int
}
