package com.example.ecommerce.data.local.staticData

import com.example.ecommerce.domain.model.ProductLocal
import com.example.ecommerce.domain.model.ProductColor
import com.example.ecommerce.domain.model.SearchSuggestion
import com.example.ecommerce.domain.model.SuggestionType

// LocalProductDataSource.kt
object LocalProductDataSource {
    // Sample product data
    val allProductLocals = listOf(
        ProductLocal(
            id = "1",
            name = "Nike Air Max",
            description = "Comfortable running shoes with air cushioning",
            price = 129.99,
            originalPrice = 149.99,
            images = listOf("https://example.com/shoe1.jpg", "https://example.com/shoe2.jpg"),
            category = "Shoes",
            brand = "Nike",
            inStock = true,
            rating = 4.5,
            reviewCount = 234,
            specifications = mapOf(
                "Material" to "Mesh",
                "Color" to "Black/White",
                "Size" to "US 9"
            ),
            colors = listOf(
                ProductColor("Black", "#000000"),
                ProductColor("White", "#FFFFFF"),
                ProductColor("Red", "#FF0000")
            ),
            sizes = listOf("US 8", "US 9", "US 10", "US 11"),
            tags = listOf("running", "sports", "comfort")
        ),
        ProductLocal(
            id = "2",
            name = "Apple iPhone 13",
            description = "Latest smartphone with advanced camera",
            price = 799.99,
            images = listOf("https://example.com/phone1.jpg"),
            category = "Electronics",
            brand = "Apple",
            inStock = true,
            rating = 4.8,
            reviewCount = 567,
            specifications = mapOf(
                "Storage" to "128GB",
                "Color" to "Midnight",
                "Screen" to "6.1 inch"
            ),
            colors = listOf(
                ProductColor("Midnight", "#1A1A1A"),
                ProductColor("Starlight", "#F5F5DC"),
                ProductColor("Blue", "#0000FF")
            ),
            tags = listOf("smartphone", "apple", "camera")
        ),
        // Add more sample products...
        ProductLocal(
            id = "3",
            name = "Levi's Jeans",
            description = "Classic denim jeans for everyday wear",
            price = 59.99,
            originalPrice = 79.99,
            images = listOf("https://example.com/jeans1.jpg"),
            category = "Clothing",
            brand = "Levi's",
            inStock = true,
            rating = 4.3,
            reviewCount = 189,
            specifications = mapOf(
                "Material" to "Denim",
                "Fit" to "Slim",
                "Waist" to "32 inch"
            ),
            colors = listOf(
                ProductColor("Blue", "#0000FF"),
                ProductColor("Black", "#000000"),
                ProductColor("Grey", "#808080")
            ),
            sizes = listOf("30", "32", "34", "36", "38"),
            tags = listOf("denim", "casual", "fashion")
        )
    )

    val trendingSearches = listOf(
        SearchSuggestion("sneakers", SuggestionType.TRENDING, 150),
        SearchSuggestion("iphone", SuggestionType.TRENDING, 120),
        SearchSuggestion("jeans", SuggestionType.TRENDING, 95),
        SearchSuggestion("laptop", SuggestionType.TRENDING, 80),
        SearchSuggestion("watch", SuggestionType.TRENDING, 70)
    )

    val categories = listOf("Shoes", "Electronics", "Clothing", "Accessories", "Home")
}