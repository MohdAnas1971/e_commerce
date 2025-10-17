package com.example.ecommerce.presentation.uiComponents.searchBar


import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

// Helper function for setting max height
fun Modifier.heightIn(max: Dp): Modifier = then(
    Modifier.sizeIn(maxHeight = max)
)



