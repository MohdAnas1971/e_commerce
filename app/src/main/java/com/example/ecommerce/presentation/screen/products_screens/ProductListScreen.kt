package com.example.ecommerce.presentation.screen.products_screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerce.domain.model.Result
import com.example.ecommerce.domain.model.UiState
import com.example.ecommerce.navigation.NavRouts
import com.example.ecommerce.presentation.screen.home_screens.HomeScreen
import com.example.ecommerce.presentation.screen.search_screen.EmptySearchResults
import com.example.ecommerce.presentation.screen.search_screen.ErrorMessage
import com.example.ecommerce.presentation.screen.search_screen.LoadingIndicator
import com.example.ecommerce.presentation.screen.search_screen.ProductGrid
import com.example.ecommerce.presentation.screen.search_screen.SearchViewModel
import com.example.ecommerce.presentation.uiComponents.bottom_app_bar.BottomNavItem
import com.example.ecommerce.presentation.uiComponents.bottom_app_bar.BottomNavigationBar
import com.example.ecommerce.presentation.uiComponents.searchBar.EnhancedCustomSearchBar
import com.example.ecommerce.presentation.uiComponents.top_app_bar.CustomAppBar


@SuppressLint("UnrememberedMutableState")
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val productState by productViewModel.productsState.collectAsState(Result.Idle)
    var searchQuery by remember { mutableStateOf("") }
    val suggestions by viewModel.suggestionState.collectAsState()
    val searchHistory by viewModel.searchHistory.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    var expanded by rememberSaveable { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CustomAppBar()
        },

        bottomBar = {
            BottomNavigationBar(
                currentRoute = "home",
                onItemClick = { item ->
                    when (item) {
                        is BottomNavItem.Home -> {
                            // Already on the home/product list
                        }
                        is BottomNavItem.Wishlist -> {
                            navController.navigate(NavRouts.WishListScreen)
                        }
                        is BottomNavItem.Cart -> {
                            navController.navigate(NavRouts.CartScreen)
                        }
                        is BottomNavItem.Search -> {
                            navController.navigate(NavRouts.SearchScreen)
                        }
                        is BottomNavItem.Settings -> {
                            navController.navigate(NavRouts.SettingScreen)
                        }
                    }
                }
            )

        }
    ) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            EnhancedCustomSearchBar(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it; viewModel.setSearchQuery(it)
                    productViewModel.loadProducts() },
                onSearch = {
                    viewModel.searchProducts()
                           },
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

            when (val state = productState) {

                is Result.Failure -> {
                    Log.d("SearchProductScreen", "ErrorMessage")
                    ErrorMessage(
                        message = state.message,
                        modifier = Modifier.weight(1f),
                        onRetry = { viewModel.searchProducts() }
                    )
                }

                is Result.Idle -> {
                    HomeScreen(navController)
                }

                is Result.Loading -> {
                    LoadingIndicator(modifier = Modifier.weight(1f))
                    Log.d("SearchProductScreen", "LoadingIndicator")
                }

                is Result.Success<*> -> {
                    val products = (state as Result.Success).data
                    if (products.isEmpty()) {
                        Log.d("SearchProductScreen", "EmptySearchResults")
                        EmptySearchResults(
                            searchQuery = searchQuery,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Log.d("SearchProductScreen", "ProductGrid")
                        ProductGrid(
                            productLocals = products,
                            navController,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

            }

        }
    }
}


@Composable
fun StarRating(
    rating: Double,
    starSize: Dp = 16.dp,
    maxStars: Int = 5
) {
    Row {
        repeat(maxStars) { index ->
            val starRating = when {
                rating >= index + 1 -> 1.0 // Full star
                rating > index -> rating - index // Partial star
                else -> 0.0 // Empty star
            }

            Box {
                // Background (empty) star
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFE0E0E0), // Light gray for empty
                    modifier = Modifier.size(starSize)
                )

                // Foreground (filled) star with clipping for partial fill
                if (starRating > 0) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFA726), // Orange for filled
                        modifier = Modifier
                            .size(starSize)
                            .clipToBounds()
                            .drawWithContent {
                                clipRect(
                                    right = size.width * starRating.toFloat()
                                ) {
                                    this@drawWithContent.drawContent()
                                }
                            }
                    )
                }
            }
        }
    }
}
