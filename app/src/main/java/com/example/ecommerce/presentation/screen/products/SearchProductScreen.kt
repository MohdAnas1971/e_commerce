package com.example.ecommerce.presentation.screen.products

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerce.R
import com.example.ecommerce.data.local.staticData.ProductCategory
import com.example.ecommerce.domain.model.product_api_models.Product
import com.example.ecommerce.domain.model.ResultIs
import com.example.ecommerce.domain.model.UiState
import com.example.ecommerce.presentation.screen.home.ProductListViewModel
import com.example.ecommerce.presentation.uiComponents.searchBar.EnhancedCustomSearchBar
import com.example.ecommerce.presentation.uiComponents.ui_cards.ProductDetailCard

@Composable
fun SearchProductScreen(
    onClick: () -> Unit={},
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    productListViewModel: ProductListViewModel =hiltViewModel()

) {
    Log.d("SearchProductScreen","Start: SearchProductScreen")

    val searchQuery by viewModel.searchQuery.collectAsState()
    val suggestions by viewModel.suggestionState.collectAsState()
    val searchHistory by viewModel.searchHistory.collectAsState()
    val searchState  by viewModel.searchState.collectAsState()
    var expanded by rememberSaveable { mutableStateOf(true) }

    val productsState by productListViewModel.productsState.collectAsState()

    Scaffold(
        topBar = {

        }
    ) { innerPadding->



        Column (
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ){
            EnhancedCustomSearchBar(
                query = searchQuery,
                onQueryChange = { viewModel.setSearchQuery(it) },
                onSearch = { viewModel.searchProducts() },
                suggestions = (suggestions as? UiState.Success)?.data ?: emptyList(),
                onSuggestionClick = { suggestion ->
                    viewModel.setSearchQuery(suggestion.text)
                    viewModel.searchProducts()
                },
                searchHistory = searchHistory,
                onClearHistory = {viewModel.clearSearchHistory()},
                onExpandedChange = {
                    expanded=it
                    onClick()          },
                expanded = expanded
            )
            Spacer(Modifier.height(8.dp))

            when (productsState) {
             /*   is UiState.Loading -> {LoadingIndicator(modifier = Modifier.weight(1f))
                    Log.d("SearchProductScreen","LoadingIndicator")
                }
                is UiState.Error ->{
                    Log.d("SearchProductScreen","ErrorMessage")
                    ErrorMessage(
                        message = (searchState as UiState.Error).message,
                        modifier = Modifier.weight(1f),
                        onRetry = { viewModel.searchProducts() }
                    )}

                is UiState.Success -> {
                    val products = (searchState as UiState.Success).data
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
                }*/

                is ResultIs.Error -> {
                    Log.d("SearchProductScreen","ErrorMessage")
                    ErrorMessage(
                        message = (productsState as ResultIs.Error).message,
                        modifier = Modifier.weight(1f),
                        onRetry = { viewModel.searchProducts() }
                    )}

                ResultIs.Idle -> TODO()
                ResultIs.Loading -> {LoadingIndicator(modifier = Modifier.weight(1f))
                    Log.d("SearchProductScreen","LoadingIndicator")
                }
                is ResultIs.Success<*> -> {
                    val products = (productsState as ResultIs.Success).data
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




@Composable
fun ProductList(categoryList: List<ProductCategory>) {
    LazyColumn(
        modifier= Modifier.fillMaxWidth().padding(horizontal = 8.dp)
    ) {
        items(categoryList.chunked(4)){rowItem->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    ProductDetailCard(136, imagRes = rowItem[0].imagRes)
                    Spacer(Modifier.height(12.dp))
                    ProductDetailCard(imagRes = rowItem[1].imagRes)
                }
                Column {
                    ProductDetailCard(imagRes = rowItem[2].imagRes)
                    Spacer(Modifier.height(12.dp))
                    ProductDetailCard(136, imagRes = rowItem[3].imagRes)
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}


@Composable
fun ProductGrid(
    productLocals: List<Product>,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {


    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(productLocals) { product ->
            ProductCard(
                productLocal = product,
                onClick = { onProductClick(product.id.toString()) }
            )
        }
    }
}


@Composable
fun ProductCard(
    productLocal: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (productLocal.images.isNotEmpty()) {
                    // Using Coil for image loading
                    AsyncImage(
                        model = productLocal.images.first(),
                        contentDescription = productLocal.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = painterResource(R.drawable.place_holder), // Optional placeholder
                        error = painterResource(R.drawable.error_image) // Optional error placeholder
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "No image available",
                        tint = Color.Gray,
                        modifier = Modifier.size(48.dp)
                    )
                }

                // Sale badge if there's a discount
                if (productLocal.discountPercentage > 0.0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(Color.Red, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "SALE",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Product Title
            Text(
                text = productLocal.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Product Brand
            Text(
                text = productLocal.brand,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price with discount calculation
            val discountedPrice = productLocal.price * (1 - productLocal.discountPercentage / 100)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (productLocal.discountPercentage > 0.0) {
                    Text(
                        text = "$${productLocal.price}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                }

                Text(
                    text = "$${"%.2f".format(discountedPrice)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                if (productLocal.discountPercentage > 0.0) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "-${"%.0f".format(productLocal.discountPercentage)}%",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating and Stock
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFA000),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "%.1f".format(productLocal.rating),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Stock status
                Text(
                    text = if (productLocal.stock > 0) "In Stock" else "Out of Stock",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (productLocal.stock > 0) Color.Green else Color.Red
                )
            }
        }
    }
}
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

// For smaller loading indicators
@Composable
fun SmallLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp),
            strokeWidth = 2.dp
        )
    }
}

@Composable
fun EmptySearchResults(
    modifier: Modifier = Modifier,
    searchQuery: String? = null,
    onSuggestionClick: (String) -> Unit = {},
    trendingSearches: List<String> = listOf("Summer Collection", "New Arrivals", "Sale", "Electronics"),
    popularCategories: List<String> = listOf("Clothing", "Shoes", "Accessories", "Electronics")
) {
    Log.d("EmptySearchResults","start :EmptySearchResults")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Section
        Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = "No results",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (searchQuery.isNullOrEmpty()) {
                "Start Exploring"
            } else {
                "No results for \"$searchQuery\""
            },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (searchQuery.isNullOrEmpty()) {
                "Search for products or browse our categories"
            } else {
                "Don't worry, let's find something else you might like"
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Trending Searches Section
        if (trendingSearches.isNotEmpty()) {
            SectionTitle(
                title = "🔥 Trending Now",
                subtitle = "What others are searching for"
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(trendingSearches) { trend ->
                    SuggestionChip(
                        text = trend,
                        icon = Icons.AutoMirrored.Filled.TrendingUp,
                        onClick = { onSuggestionClick(trend) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Popular Categories Section
        if (popularCategories.isNotEmpty()) {
            SectionTitle(
                title = "📦 Popular Categories",
                subtitle = "Browse by category"
            )

            // Replace the LazyVerticalGrid with this:
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                popularCategories.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { category ->
                            CategoryCard(
                                title = category,
                                onClick = { onSuggestionClick(category) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // Add empty space if the row has only 1 item
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Search Tips Section
        SectionTitle(
            title = "💡 Search Tips",
            subtitle = "How to find what you're looking for"
        )

        val searchTips = remember {
            listOf(
                "Try using more general terms",
                "Check your spelling",
                "Use product names or brands",
                "Try different keywords"
            )
        }

       /* Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            searchTips.forEach { tip ->
                SearchTipItem(tip = tip)
            }
        }
*/
        Spacer(modifier = Modifier.height(16.dp))

        // Browse All Button
        Button(
            onClick = { onSuggestionClick("") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Browse All Products")
        }
    }
    Log.d("EmptySearchResults","End :EmptySearchResults")

}

@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {
    Log.d("EmptySearchResults","Start :SectionTitle")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
    Log.d("EmptySearchResults","End :SectionTitle")

}

@Composable
private fun SuggestionChip(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    var selected by rememberSaveable { mutableStateOf(false) }
    // Replace AssistChip with FilterChip (available in older Material 3 versions)
    FilterChip(
        selected = false,
        onClick ={onClick();selected=true} ,
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = MaterialTheme.colorScheme.outlineVariant,
            borderWidth = 1.dp,
            enabled = true,
            selected = selected,
        )
    )
}


@Composable
private fun CategoryCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier= Modifier
) {
    Log.d("EmptySearchResults","Start :CategoryCard")

    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    Log.d("EmptySearchResults","End :CategoryCard")

}

@Composable
private fun SearchTipItem(tip: String) {
    Log.d("EmptySearchResults","Start :SearchTipItem")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Lightbulb,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = tip,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }
    Log.d("EmptySearchResults","End :SearchTipItem")
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = "Error",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Something went wrong",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )

        if (onRetry != null) {
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Retry",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Try Again")
            }
        }
    }
}

// For smaller error messages
@Composable
fun SmallErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Error",
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}