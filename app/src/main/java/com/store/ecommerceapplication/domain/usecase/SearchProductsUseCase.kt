package com.store.ecommerceapplication.domain.usecase

import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.ProductRepository
import com.store.ecommerceapplication.domain.util.Result
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): Result<List<Product>> {
        return if (query.isBlank()) {
            // If a query is empty, return all products
            repository.getProducts()
        } else {
            // Otherwise, search with the query
            repository.searchProducts(query)
        }
    }
}
