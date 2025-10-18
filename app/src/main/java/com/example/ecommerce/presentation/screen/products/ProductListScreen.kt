package com.example.ecommerce.presentation.screen.products

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerce.domain.model.ResultIs
import com.example.ecommerce.domain.model.UiState
import com.example.ecommerce.presentation.screen.home.HomeScreen
import com.example.ecommerce.presentation.uiComponents.CustomAppBar
import com.example.ecommerce.presentation.uiComponents.searchBar.EnhancedCustomSearchBar


@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: SearchViewModel= hiltViewModel(),
    productViewModel: ProductViewModel= hiltViewModel()
){

    val  productState by productViewModel.productState.collectAsState(ResultIs.Idle)
    var searchQuery by remember { mutableStateOf("") }
    val suggestions by viewModel.suggestionState.collectAsState()
    val searchHistory by viewModel.searchHistory.collectAsState()
    val searchState  by viewModel.searchState.collectAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomAppBar()
        }
    ) { innerPadding ->

        Column (
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            EnhancedCustomSearchBar(
                query = searchQuery,
                onQueryChange = {searchQuery=it; viewModel.setSearchQuery(it);productViewModel.loadProducts()},
                onSearch = { viewModel.searchProducts() },
                suggestions = (suggestions as? UiState.Success)?.data ?: emptyList(),
                onSuggestionClick = { suggestion ->
                    viewModel.setSearchQuery(suggestion.text)
                    viewModel.searchProducts()
                },
                searchHistory = searchHistory,
                onClearHistory = { viewModel.clearSearchHistory() },
                onExpandedChange = {
                    expanded = it
                   // onClick()
                },
                expanded = expanded
            )

            Spacer(Modifier.height(8.dp))

            when (val state =productState) {

                is ResultIs.Error -> {
                    Log.d("SearchProductScreen","ErrorMessage")
                    ErrorMessage(
                        message = state.message,
                        modifier = Modifier.weight(1f),
                        onRetry = { viewModel.searchProducts() }
                    )}

                is ResultIs.Idle ->{ HomeScreen(navController)}

                is ResultIs.Loading -> {LoadingIndicator(modifier = Modifier.weight(1f))
                    Log.d("SearchProductScreen","LoadingIndicator")
                }

                is ResultIs.Success<*> -> {
                    val products = (state as ResultIs.Success).data
                    if (products.isEmpty()) {
                        Log.d("SearchProductScreen","EmptySearchResults")
                        EmptySearchResults(
                            searchQuery = searchQuery,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Log.d("SearchProductScreen","ProductGrid")

                        ProductGrid(
                            productLocals = products,
                            onProductClick = {},
                            modifier = Modifier.weight(1f)
                        )
                    }
                }


            }

        }
    }
    }
