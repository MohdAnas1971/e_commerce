package com.example.ecommerce.domain.repo

import com.example.ecommerce.domain.model.product_api_models.Product
import com.example.ecommerce.domain.model.Result
import com.example.ecommerce.domain.model.product_api_models.CategoryItem


interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>

    suspend fun getProductById(id: Int): Result<Product>

    suspend fun searchProducts(query: String): Result<List<Product>>
    suspend fun getCategories(): Result<List<CategoryItem>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>

}
