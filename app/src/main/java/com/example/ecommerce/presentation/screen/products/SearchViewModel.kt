package com.example.ecommerce.presentation.screen.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.data.repository.LocalLocalProductRepo
import com.example.ecommerce.domain.model.ProductLocal
import com.example.ecommerce.domain.model.SearchSuggestion
import com.example.ecommerce.domain.model.UiState
import com.example.ecommerce.domain.repo.LocalProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// SearchViewModel.kt
@HiltViewModel
class SearchViewModel @Inject constructor(
private val str: String
) : ViewModel() {

    private val repository: LocalProductRepo = LocalLocalProductRepo()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchState = MutableStateFlow<UiState<List<ProductLocal>>>(UiState.Success(emptyList()))
    val searchState: StateFlow<UiState<List<ProductLocal>>> = _searchState.asStateFlow()

    private val _suggestionState = MutableStateFlow<UiState<List<SearchSuggestion>>>(UiState.Success(emptyList()))
    val suggestionState: StateFlow<UiState<List<SearchSuggestion>>> = _suggestionState.asStateFlow()

    private val _searchHistory = MutableStateFlow<List<String>>(emptyList())
    val searchHistory: StateFlow<List<String>> = _searchHistory.asStateFlow()

    private val _filters = MutableStateFlow<Map<String, String>>(emptyMap())
    val filters: StateFlow<Map<String, String>> = _filters.asStateFlow()

    init {
        loadSearchHistory()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isNotEmpty()) {
            getSearchSuggestions(query)
        } else {
            _suggestionState.value = UiState.Success(emptyList())
        }
    }
    fun searchProducts() {
        viewModelScope.launch {
            val query = _searchQuery.value
            if (query.isNotEmpty()) {
                _searchState.value = UiState.Loading
                repository.saveSearchQuery(query)
                loadSearchHistory()

                val results = repository.searchProducts(query, _filters.value)
                _searchState.value = UiState.Success(results)
            }
        }
    }

    private fun getSearchSuggestions(query: String) {
        val suggestions = repository.getSearchSuggestions(query)
        _suggestionState.value = UiState.Success(suggestions)
    }

    private fun loadSearchHistory() {
        _searchHistory.value = repository.getSearchHistory()
    }

    fun clearSearchHistory() {
        repository.clearSearchHistory()
        loadSearchHistory()
    }

    fun updateFilter(key: String, value: String) {
        val currentFilters = _filters.value.toMutableMap()
        currentFilters[key] = value
        _filters.value = currentFilters
    }

    fun removeFilter(key: String) {
        val currentFilters = _filters.value.toMutableMap()
        currentFilters.remove(key)
        _filters.value = currentFilters
    }

    fun clearFilters() {
        _filters.value = emptyMap()
    }
}