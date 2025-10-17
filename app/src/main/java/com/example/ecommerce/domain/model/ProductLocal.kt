package com.example.ecommerce.domain.model

// Product.kt
data class ProductLocal(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val originalPrice: Double? = null,
    val currency: String = "$",
    val images: List<String>,
    val category: String,
    val brand: String,
    val inStock: Boolean,
    val rating: Double,
    val reviewCount: Int,
    val specifications: Map<String, String> = emptyMap(),
    val colors: List<ProductColor> = emptyList(),
    val sizes: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)


data class ProductColor(
    val name: String,
    val hexCode: String,
    val imageUrl: String? = null
)

data class ProductCategory(
    val id: String,
    val name: String,
    val imageUrl: String,
    val productCount: Int
)

data class SearchSuggestion(
    val text: String,
    val type: SuggestionType,
    val searchCount: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)

/*data class SearchSuggestion(
    val text: String,
    val type: SuggestionType,
    val icon: @Composable (() -> Unit)? = null
)*/
enum class SuggestionType {
    HISTORY, TRENDING, RECOMMENDED, CATEGORY
}

data class ApiResponse<T>(
    val data: T? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)


sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

