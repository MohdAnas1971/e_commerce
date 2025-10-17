package com.example.ecommerce.domain.repo

import com.example.ecommerce.domain.model.ProductLocal

interface DummyProductRepo {
    suspend fun getProducts(): Result<List<ProductLocal>>
}
