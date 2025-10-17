package com.example.ecommerce.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.data.repository.LocalLocalProductRepo
import com.example.ecommerce.data.repository.ProductRepositoryImpl
import com.example.ecommerce.domain.model.product_api_models.Product
import com.example.ecommerce.domain.model.ProductLocal
import com.example.ecommerce.domain.model.ResultIs
import com.example.ecommerce.domain.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// ProductListViewModel.kt
@HiltViewModel
class ProductListViewModel @Inject constructor(

    private val productRepositoryImpl: ProductRepositoryImpl
) : ViewModel() {

    private val repository: com.example.ecommerce.domain.repo.LocalProductRepo = LocalLocalProductRepo()

    private val _localProductsState = MutableStateFlow<UiState<List<ProductLocal>>>(UiState.Loading)
    val localProductsState: StateFlow<UiState<List<ProductLocal>>> = _localProductsState.asStateFlow()

    private val _productsState = MutableStateFlow<ResultIs<List<Product>>>(ResultIs.Idle)
    val productsState: StateFlow<ResultIs<List<Product>>> = _productsState.asStateFlow()
    private val _sortOption = MutableStateFlow(SortOption.RELEVANCE)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()




    init {
        getProducts()
        loadProducts()
    }

    fun getProducts() {

        _productsState.value = ResultIs.Loading
        viewModelScope.launch {
            val currentProducts = productRepositoryImpl.getProducts()
            _productsState.value = currentProducts
        }
    }


  private  fun loadProducts(category: String? = null) {
        _localProductsState.value = UiState.Loading
        _selectedCategory.value = category

        viewModelScope.launch {
            val products = if (category != null) {
                repository.getProductsByCategory(category)
            } else {
                repository.getTrendingProducts()
            }
            _localProductsState.value = UiState.Success(sortProducts(products, _sortOption.value))
        }
    }



    fun sortProducts(option: SortOption) {
        _sortOption.value = option
        val currentProducts = (localProductsState.value as? UiState.Success)?.data ?: return
        _localProductsState.value = UiState.Success(sortProducts(currentProducts, option))
    }


   private  fun sortProducts(productLocals: List<ProductLocal>, option: SortOption): List<ProductLocal> {
        return when (option) {
            SortOption.PRICE_LOW_TO_HIGH -> productLocals.sortedBy { it.price }
            SortOption.PRICE_HIGH_TO_LOW -> productLocals.sortedByDescending { it.price }
            SortOption.RATING -> productLocals.sortedByDescending { it.rating }
            SortOption.NEWEST -> productLocals.sortedByDescending { it.createdAt }
            SortOption.RELEVANCE -> productLocals
        }
    }

    fun refresh() {
        loadProducts(_selectedCategory.value)
    }
}

enum class SortOption {
    RELEVANCE, PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, RATING, NEWEST
}