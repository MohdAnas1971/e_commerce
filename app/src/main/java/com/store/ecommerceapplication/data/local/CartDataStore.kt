package com.store.ecommerceapplication.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.store.ecommerceapplication.domain.model.CartItem
import com.store.ecommerceapplication.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class CartDataStore(private val context: Context) {
    
    companion object {
        private val Context.cartDataStore: DataStore<Preferences> by preferencesDataStore("cart_preferences")
        private val CART_ITEMS = stringPreferencesKey("cart_items")
    }
    
    private val json = Json { 
        ignoreUnknownKeys = true
        isLenient = true
    }
    
    val cartItems: Flow<List<CartItem>> = context.cartDataStore.data.map { preferences ->
        val itemsJson = preferences[CART_ITEMS] ?: "[]"
        try {
            json.decodeFromString<List<CartItem>>(itemsJson)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun addToCart(product: Product, quantity: Int = 1) {
        context.cartDataStore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            // Check if product already exists in cart
            val existingItemIndex = currentItems.indexOfFirst { it.product.id == product.id }
            val updatedItems = if (existingItemIndex != -1) {
                // Update quantity if product exists
                currentItems.toMutableList().apply {
                    this[existingItemIndex] = this[existingItemIndex].copy(
                        quantity = this[existingItemIndex].quantity + quantity
                    )
                }
            } else {
                // Add new item
                currentItems + CartItem(product, quantity)
            }
            
            preferences[CART_ITEMS] = json.encodeToString(updatedItems)
        }
    }
    
    suspend fun removeFromCart(productId: Int) {
        context.cartDataStore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedItems = currentItems.filter { it.product.id != productId }
            preferences[CART_ITEMS] = json.encodeToString(updatedItems)
        }
    }
    
    suspend fun updateQuantity(productId: Int, quantity: Int) {
        context.cartDataStore.edit { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            
            val updatedItems = currentItems.map { item ->
                if (item.product.id == productId) {
                    item.copy(quantity = quantity)
                } else {
                    item
                }
            }.filter { it.quantity > 0 } // Remove items with 0 quantity
            
            preferences[CART_ITEMS] = json.encodeToString(updatedItems)
        }
    }
    
    suspend fun clearCart() {
        context.cartDataStore.edit { preferences ->
            preferences[CART_ITEMS] = "[]"
        }
    }
    
    suspend fun getCartItemCount(): Int {
        var count = 0
        context.cartDataStore.data.map { preferences ->
            val currentJson = preferences[CART_ITEMS] ?: "[]"
            val currentItems = try {
                json.decodeFromString<List<CartItem>>(currentJson)
            } catch (e: Exception) {
                emptyList()
            }
            count = currentItems.sumOf { it.quantity }
        }.collect { }
        return count
    }
}
