package com.store.ecommerceapplication.domain.usecase

import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.repository.ProductRepository
import com.store.ecommerceapplication.domain.util.Result
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(category: String): Result<List<Product>> {
        return repository.getProductsByCategory(category)
    }
}
