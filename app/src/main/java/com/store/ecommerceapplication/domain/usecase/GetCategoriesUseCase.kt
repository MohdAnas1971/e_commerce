package com.store.ecommerceapplication.domain.usecase

import com.store.ecommerceapplication.domain.model.CategoryItem
import com.store.ecommerceapplication.domain.repository.ProductRepository
import com.store.ecommerceapplication.domain.util.Result
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<CategoryItem>> {
        return repository.getCategories()
    }
}
