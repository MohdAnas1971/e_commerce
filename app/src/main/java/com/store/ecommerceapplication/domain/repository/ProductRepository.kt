package com.store.ecommerceapplication.domain.repository

import com.store.ecommerceapplication.domain.model.CategoryItem
import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.util.Result

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun searchProducts(query: String): Result<List<Product>>
    suspend fun getCategories(): Result<List<CategoryItem>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>
}
