package com.store.ecommerceapplication.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.store.ecommerceapplication.domain.model.CartItem
import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CartState(
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalPrice: Double = 0.0,
    val totalItems: Int = 0
)

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state.asStateFlow()
    
    init {
        loadCartItems()
    }
    
    private fun loadCartItems() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                cartRepository.getCartItems().collect { items ->
                    val total = items.sumOf { 
                        val discountedPrice = it.product.price * (1 - it.product.discountPercentage / 100)
                        discountedPrice * it.quantity 
                    }
                    val totalItems = items.sumOf { it.quantity }
                    
                    _state.value = _state.value.copy(
                        cartItems = items,
                        totalPrice = total,
                        totalItems = totalItems,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load cart"
                )
            }
        }
    }
    
    fun addToCart(product: Product, quantity: Int = 1) {
        viewModelScope.launch {
            try {
                cartRepository.addToCart(product, quantity)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to add to cart"
                )
            }
        }
    }
    
    fun removeFromCart(productId: Int) {
        viewModelScope.launch {
            try {
                cartRepository.removeFromCart(productId)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to remove from cart"
                )
            }
        }
    }
    
    fun updateQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                cartRepository.updateQuantity(productId, quantity)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to update quantity"
                )
            }
        }
    }
    
    fun clearCart() {
        viewModelScope.launch {
            try {
                cartRepository.clearCart()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message ?: "Failed to clear cart"
                )
            }
        }
    }
}
