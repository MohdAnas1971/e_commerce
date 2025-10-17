package com.example.ecommerce.data.repository

import android.util.Log
import com.example.ecommerce.data.remote.NetworkModule
import com.example.ecommerce.data.remote.ProductApiService
import com.example.ecommerce.domain.repo.ProductRepository
import com.example.ecommerce.domain.model.product_api_models.Product
import javax.inject.Inject
import com.example.ecommerce.domain.model.ResultIs


class ProductRepositoryImpl @Inject constructor(
    networkModule: NetworkModule
) : ProductRepository {
    private val apiService:  ProductApiService= networkModule.productApiService
    override suspend fun getProducts(): ResultIs<List<Product>> {
        return try {
            Log.d("ProductRepository", "Fetching products from API")
            val response = apiService.getProducts()
            Log.d("ProductRepository", "Received ${response.products.size} products")
            ResultIs.Success(response.products)
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching products: ${e.message}", e)
            ResultIs.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }
}



