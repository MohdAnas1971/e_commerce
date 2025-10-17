package com.example.ecommerce.presentation.screen.products

import androidx.lifecycle.ViewModel
import com.example.ecommerce.data.repository.LocalLocalProductRepo
import com.example.ecommerce.domain.model.ProductLocal
import com.example.ecommerce.domain.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ProductDetailViewModel.kt
class ProductDetailViewModel : ViewModel() {

    private val repository: com.example.ecommerce.domain.repo.LocalProductRepo = LocalLocalProductRepo()

    private val _productLocalState = MutableStateFlow<UiState<ProductLocal>>(UiState.Loading)
    val productLocalState: StateFlow<UiState<ProductLocal>> = _productLocalState.asStateFlow()

    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor: StateFlow<String?> = _selectedColor.asStateFlow()

    private val _selectedSize = MutableStateFlow<String?>(null)
    val selectedSize: StateFlow<String?> = _selectedSize.asStateFlow()

    private val _quantity = MutableStateFlow(1)
    val quantity: StateFlow<Int> = _quantity.asStateFlow()

    private val _relatedProductsState = MutableStateFlow<UiState<List<ProductLocal>>>(UiState.Success(emptyList()))
    val relatedProductsState: StateFlow<UiState<List<ProductLocal>>> = _relatedProductsState.asStateFlow()

    fun loadProduct(productId: String) {
        _productLocalState.value = UiState.Loading

        val product = repository.getProductById(productId)
        if (product != null) {
            _productLocalState.value = UiState.Success(product)
            loadRelatedProducts(product.category, productId)
            _selectedColor.value = product.colors.firstOrNull()?.name
            _selectedSize.value = product.sizes.firstOrNull()
        } else {
            _productLocalState.value = UiState.Error("Product not found")
        }
    }

    private fun loadRelatedProducts(category: String, excludeProductId: String) {
        val relatedProducts = repository.getProductsByCategory(category)
            .filter { it.id != excludeProductId }
            .shuffled()
            .take(4)

        _relatedProductsState.value = UiState.Success(relatedProducts)
    }

    fun selectColor(colorName: String) {
        _selectedColor.value = colorName
    }

    fun selectSize(size: String) {
        _selectedSize.value = size
    }

    fun increaseQuantity() {
        _quantity.value += 1
    }

    fun decreaseQuantity() {
        if (_quantity.value > 1) {
            _quantity.value -= 1
        }
    }

    fun addToCart(): Boolean {
        val currentProduct = (productLocalState.value as? UiState.Success)?.data ?: return false
        val color = _selectedColor.value
        val size = _selectedSize.value

        // Validate selection for products that require color/size
        if (currentProduct.colors.isNotEmpty() && color == null) return false
        if (currentProduct.sizes.isNotEmpty() && size == null) return false

        // Here you would typically save to local storage or state
        return true
    }
}