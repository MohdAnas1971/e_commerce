package com.example.ecommerce.domain.repo



import com.example.ecommerce.domain.model.ProductLocal
import com.example.ecommerce.domain.model.SearchSuggestion


interface LocalProductRepo {
    fun searchProducts(query: String, filters: Map<String, String>): List<ProductLocal>
    fun getProductById(id: String): ProductLocal?
    fun getProductsByCategory(category: String): List<ProductLocal>
    fun getTrendingProducts(): List<ProductLocal>
    fun getSearchSuggestions(query: String): List<SearchSuggestion>
    fun saveSearchQuery(query: String)
    fun getSearchHistory(): List<String>
    fun clearSearchHistory()
}