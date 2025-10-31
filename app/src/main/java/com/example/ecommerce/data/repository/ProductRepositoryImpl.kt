package com.example.ecommerce.data.repository

import android.util.Log
import com.example.ecommerce.data.remote.NetworkModule
import com.example.ecommerce.data.remote.ProductApiService
import com.example.ecommerce.domain.repo.ProductRepository
import com.example.ecommerce.domain.model.product_api_models.Product
import javax.inject.Inject
import com.example.ecommerce.domain.model.Result
import com.example.ecommerce.domain.model.product_api_models.CategoryItem


class ProductRepositoryImpl @Inject constructor(
    networkModule: NetworkModule
) : ProductRepository {
    private val apiService:  ProductApiService= networkModule.productApiService

    override suspend fun getProductById(id: Int): Result<Product> {
       return try {
           val response = apiService.getProductById(id)
           Result.Success(response)

       }catch (e: Exception){
           Result.Failure(e.localizedMessage?:"Unknown Error")
       }
    }
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



