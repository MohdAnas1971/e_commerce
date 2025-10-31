package com.store.ecommerceapplication.Presentation.Products

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.store.ecommerceapplication.Navigation.Routes
import com.store.ecommerceapplication.Presentation.Components.BottomNavItem
import com.store.ecommerceapplication.Presentation.Components.BottomNavigationBar
import com.store.ecommerceapplication.R
import com.store.ecommerceapplication.domain.model.Product
import com.store.ecommerceapplication.domain.util.Result
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val productsState by productViewModel.productsState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Center logo
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.brand_name_icon),
                            contentDescription = "Brand Logo",
                            modifier = Modifier.height(32.dp)
                        )
                    }
                },
                navigationIcon = {
                    // Left menu icon
                    IconButton(onClick = { /* Handle a menu */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    // Right profile avatar
                    IconButton(onClick = { /* Handle profile */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.profile_avatar),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFFE0E0E0), CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "home",
                onItemClick = { item ->
                    when (item) {
                        is BottomNavItem.Home -> {
                            // Already on a home/product list
                        }
                        is BottomNavItem.Wishlist -> {
                            navController.navigate(Routes.WishlistScreen)
                        }
                        is BottomNavItem.Cart -> {
                            navController.navigate(Routes.CartScreen)
                        }
                        is BottomNavItem.Search -> {
                            // TODO: Navigate to search
                        }
                        is BottomNavItem.Settings -> {
                            // TODO: Navigate to settings
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFf9f9f9))
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { 
                    searchQuery = it
                    // Trigger search on every text change
                    productViewModel.searchProducts(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { 
                    Text(
                        text = "Search any Product..",
                        color = Color(0xFFBBBBBB),
                        fontSize = 14.sp
                    ) 
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_magnifier),
                        contentDescription = "Search",
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { /* Handle voice search */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_mic),
                            contentDescription = "Voice Search",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            // Banner Pager
            BannerPager()

            // Items count and filter row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (val state = productsState) {
                    is Result.Success -> {
                        Text(
                            text = "${state.data.size} Items",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    else -> {
                        Text(
                            text = "Loading...",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Row {
                    TextButton(
                        onClick = { /* Handle sort */ }
                    ) {
                        Text("Sort")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Sort",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    
                    TextButton(
                        onClick = { /* Handle filter */ }
                    ) {
                        Text("Filter")
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Filter",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Category Section
            CategorySection(
                viewModel = productViewModel
            )

            // Product Grid
            when (val state = productsState) {
                is Result.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is Result.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.data) { product ->
                            ProductCard(
                                product = product,
                                onClick = { 
                                    navController.navigate(Routes.ProductDetailScreen(product.id))
                                }
                            )
                        }
                    }
                }
                is Result.Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error: ${state.message}",
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(
                                onClick = { productViewModel.retryLoading() }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
                Result.Idle -> {
                    // Initial state
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    
    // Share function
    @SuppressLint("DefaultLocale")
    fun shareProduct(product: Product) {
        val shareText = "Check out this amazing product: ${product.title}\n\n" +
                "${product.description}\n\n" +
                "Price: ₹${String.format("%.0f", product.price * 83)}\n" +
                "Rating: ${String.format("%.1f", product.rating)} stars\n\n" +
                "Get it now on EcoMart!"
        
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            putExtra(Intent.EXTRA_SUBJECT, "Check out ${product.title}")
        }
        val chooserIntent = Intent.createChooser(shareIntent, "Share Product")
        context.startActivity(chooserIntent)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Product Image
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5)),
                contentScale = ContentScale.Fit,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Image",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Product Title
            Text(
                text = product.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Product Description
            Text(
                text = product.description,
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price and Rating Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    // Discounted Price
                    Text(
                        text = "₹${String.format("%.0f", product.price * 83)}", // Convert to INR approximately
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    
                    // Original Price (if there's a discount)
                    if (product.discountPercentage > 0) {
                        val originalPrice = product.price / (1 - product.discountPercentage / 100)
                        Text(
                            text = "₹${String.format("%.0f", originalPrice * 83)}",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                // Rating and Share
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRating(
                        rating = product.rating,
                        starSize = 14.dp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f", product.rating),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { shareProduct(product) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
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

data class Category(
    val name: String,
    val imageRes: Int,
    val slug: String
)

@Composable
fun CategorySection(viewModel: ProductViewModel) {
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    
    // Static category mapping for UI images
    val categoryImageMap = mapOf(
        "beauty" to R.drawable.round_beauty_image,
        "fragrances" to R.drawable.round_fashion_image,
        "furniture" to R.drawable.round_kids_image,
        "mens-shirts" to R.drawable.round_mens_image,
        "mens-shoes" to R.drawable.round_mens_image,
        "mens-watches" to R.drawable.round_mens_image,
        "womens-bags" to R.drawable.round_womens_image,
        "womens-dresses" to R.drawable.round_womens_image,
        "womens-jewellery" to R.drawable.round_womens_image,
        "womens-shoes" to R.drawable.round_womens_image,
        "womens-watches" to R.drawable.round_womens_image
    )
    
    // Use static categories with UI images
    val categories = listOf(
        Category("Beauty", R.drawable.round_beauty_image, "beauty"),
        Category("Fashion", R.drawable.round_fashion_image, "fragrances"),
        Category("Kids", R.drawable.round_kids_image, "furniture"),
        Category("Mens", R.drawable.round_mens_image, "mens-shirts"),
        Category("Womens", R.drawable.round_womens_image, "womens-dresses")
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                isSelected = selectedCategory == category.slug,
                onClick = {
                    if (selectedCategory == category.slug) {
                        viewModel.clearCategoryFilter()
                    } else {
                        viewModel.filterByCategory(category.slug)
                    }
                }
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable { onClick() }
    ) {
        // Circular Image
        Image(
            painter = painterResource(id = category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(
                    width = if (isSelected) 3.dp else 2.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFFE0E0E0),
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Category Name
        Text(
            text = category.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun BannerPager() {
    val banners = listOf(
        R.drawable.product_banner_1,
        R.drawable.product_banner_2,
        R.drawable.product_banner_3
    )
    
    val pagerState = rememberPagerState(pageCount = { banners.size })
    val coroutineScope = rememberCoroutineScope()
    
    // Auto-scroll effect
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // 3-second delay
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = banners[page]),
                    contentDescription = "Banner ${page + 1}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        
        // Page indicators
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(banners.size) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) 
                                Color.White 
                            else 
                                Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}
