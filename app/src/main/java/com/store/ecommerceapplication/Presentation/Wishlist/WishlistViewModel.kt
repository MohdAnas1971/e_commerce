package com.store.ecommerceapplication.Presentation.Wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WishlistState(
    val allProducts: List<Product> = emptyList(),
    val filteredProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistRepository: WishlistRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(WishlistState())
    val state: StateFlow<WishlistState> = _state.asStateFlow()
    
    init {
        loadWishlistProducts()
    }
    
    private fun loadWishlistProducts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                wishlistRepository.getWishlistProducts().collect { products ->
                    _state.value = _state.value.copy(
                        allProducts = products,
                        filteredProducts = products,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load wishlist"
                )
            }
        }
    }
    
    fun searchProducts(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        
        val filtered = if (query.isBlank()) {
            _state.value.allProducts
        } else {
            _state.value.allProducts.filter { product ->
                product.title.contains(query, ignoreCase = true) ||
                product.description.contains(query, ignoreCase = true) ||
                product.brand.contains(query, ignoreCase = true) ||
                product.category.contains(query, ignoreCase = true)
            }
        }
        
        _state.value = _state.value.copy(filteredProducts = filtered)
    }
    
    fun addToWishlist(product: Product) {
        viewModelScope.launch {
            try {
                wishlistRepository.addToWishlist(product)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to add to wishlist"
                )
            }
        }
    }
    
    fun removeFromWishlist(productId: Int) {
        viewModelScope.launch {
            try {
                wishlistRepository.removeFromWishlist(productId)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to remove from wishlist"
                )
            }
        }
    }
    
    suspend fun isInWishlist(productId: Int): Boolean {
        return try {
            wishlistRepository.isInWishlist(productId)
        } catch (e: Exception) {
            false
        }
    }
}
