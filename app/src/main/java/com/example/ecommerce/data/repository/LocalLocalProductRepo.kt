package com.example.ecommerce.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.ecommerce.data.local.staticData.LocalProductDataSource
import com.example.ecommerce.domain.model.ProductLocal
import com.example.ecommerce.domain.model.SearchSuggestion
import com.example.ecommerce.domain.model.SuggestionType
import com.example.ecommerce.domain.repo.LocalProductRepo

// ProductRepository.kt

class LocalLocalProductRepo : LocalProductRepo {
    private val searchHistory = mutableListOf<String>()
    private val searchCount = mutableMapOf<String, Int>()

    override fun searchProducts(query: String, filters: Map<String, String>): List<ProductLocal> {
        return LocalProductDataSource.allProductLocals.filter { product ->
            val matchesQuery = product.name.contains(query, ignoreCase = true) ||
                    product.description.contains(query, ignoreCase = true) ||
                    product.tags.any { it.contains(query, ignoreCase = true) }

            val matchesFilters = filters.all { (key, value) ->
                when (key) {
                    "category" -> product.category.equals(value, ignoreCase = true)
                    "brand" -> product.brand.equals(value, ignoreCase = true)
                    "price_min" -> product.price >= value.toDouble()
                    "price_max" -> product.price <= value.toDouble()
                    "inStock" -> product.inStock == value.toBoolean()
                    else -> true
                }
            }

            matchesQuery && matchesFilters
        }
    }

    override fun getProductById(id: String): ProductLocal? {
        return LocalProductDataSource.allProductLocals.find { it.id == id }
    }

    override fun getProductsByCategory(category: String): List<ProductLocal> {
        return LocalProductDataSource.allProductLocals.filter { it.category.equals(category, ignoreCase = true) }
    }

    override fun getTrendingProducts(): List<ProductLocal> {
        return LocalProductDataSource.allProductLocals.shuffled().take(6)
    }

    override fun getSearchSuggestions(query: String): List<SearchSuggestion> {
        val suggestions = mutableListOf<SearchSuggestion>()

        // Add search history matches
        searchHistory.filter { it.contains(query, ignoreCase = true) }
            .take(3)
            .forEach { suggestions.add(SearchSuggestion(it, SuggestionType.HISTORY)) }

        // Add trending matches
        LocalProductDataSource.trendingSearches
            .filter { it.text.contains(query, ignoreCase = true) }
            .take(2)
            .forEach { suggestions.add(it) }

        // Add category matches
        LocalProductDataSource.categories
            .filter { it.contains(query, ignoreCase = true) }
            .take(2)
            .forEach { suggestions.add(SearchSuggestion(it, SuggestionType.CATEGORY)) }

        // Add product name matches
        LocalProductDataSource.allProductLocals
            .filter { it.name.contains(query, ignoreCase = true) }
            .take(3)
            .map { SearchSuggestion(it.name, SuggestionType.RECOMMENDED) }
            .forEach { suggestions.add(it) }

        return suggestions.distinctBy { it.text }
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun saveSearchQuery(query: String) {
        if (query.isNotBlank()) {
            searchHistory.remove(query)
            searchHistory.add(0, query)
            if (searchHistory.size > 10) {
                searchHistory.removeLast()
            }

            searchCount[query] = (searchCount[query] ?: 0) + 1
        }
    }

    override fun getSearchHistory(): List<String> {
        return searchHistory.toList()
    }

    override fun clearSearchHistory() {
        searchHistory.clear()
        searchCount.clear()
    }

}



