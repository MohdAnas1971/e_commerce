package com.example.ecommerce.domain.repo

import com.example.ecommerce.domain.model.product_api_models.Product
import com.example.ecommerce.domain.model.ResultIs


interface ProductRepository {
    suspend fun getProducts(): ResultIs<List<Product>>

}
