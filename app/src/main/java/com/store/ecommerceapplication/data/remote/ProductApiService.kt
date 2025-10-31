package com.store.ecommerceapplication.data.remote

import com.store.ecommerceapplication.domain.model.CategoryItem
import com.store.ecommerceapplication.domain.model.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ProductApiService(private val client: HttpClient) {
    
    suspend fun getProducts(limit: Int = 0): ProductResponse {
        return client.get("products") {
            parameter("limit", limit)
        }.body()
    }
    
    suspend fun searchProducts(query: String): ProductResponse {
        return client.get("products/search") {
            parameter("q", query)
        }.body()
    }
    
    suspend fun getCategories(): List<CategoryItem> {
        return client.get("products/categories").body()
    }
    
    suspend fun getProductsByCategory(category: String): ProductResponse {
        return client.get("products/category/$category").body()
    }


}
