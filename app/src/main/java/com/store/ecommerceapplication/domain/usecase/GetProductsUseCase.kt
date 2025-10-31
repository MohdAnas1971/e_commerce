package com.store.ecommerceapplication.domain.usecase

import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.ProductRepository
import com.store.ecommerceapplication.domain.util.Result
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}
