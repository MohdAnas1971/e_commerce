package com.store.ecommerceapplication.data.repositoryimpl

import android.util.Log
import com.store.ecommerceapplication.data.remote.ProductApiService
import com.store.ecommerceapplication.domain.model.CategoryItem
import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.ProductRepository
import com.store.ecommerceapplication.domain.util.Result

class ProductRepositoryImpl(
    private val apiService: ProductApiService
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            Log.d("ProductRepository", "Fetching products from API")
            val response = apiService.getProducts()
            Log.d("ProductRepository", "Received ${response.products.size} products")
            Result.Success(response.products)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products: ${e.message}", e)
            Result.Failure(e.localizedMessage ?: "Unknown error occurred")
        }
    }
    
    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return try {
            Log.d("ProductRepository", "Searching products with query: $query")
            val response = apiService.searchProducts(query)
            Log.d("ProductRepository", "Found ${response.products.size} products")
            Result.Success(response.products)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error searching products: ${e.message}", e)
            Result.Failure(e.localizedMessage ?: "Unknown error occurred")
        }
    }
    
    override suspend fun getCategories(): Result<List<CategoryItem>> {
        return try {
            Log.d("ProductRepository", "Fetching categories from API")
            val categories = apiService.getCategories()
            Log.d("ProductRepository", "Received ${categories.size} categories")
            Result.Success(categories)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching categories: ${e.message}", e)
            Result.Failure(e.localizedMessage ?: "Unknown error occurred")
        }
    }
    
    override suspend fun getProductsByCategory(category: String): Result<List<Product>> {
        return try {
            Log.d("ProductRepository", "Fetching products for category: $category")
            val response = apiService.getProductsByCategory(category)
            Log.d("ProductRepository", "Found ${response.products.size} products in category")
            Result.Success(response.products)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products by category: ${e.message}", e)
            Result.Failure(e.localizedMessage ?: "Unknown error occurred")
        }
    }
}
