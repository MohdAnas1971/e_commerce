package com.example.ecommerce.presentation.uiComponents.searchBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerce.navigation.NavRouts

@Composable
fun DummySearchBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Search Input Field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Gray, RoundedCornerShape(28.dp))
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.surface)
                .clickable(onClick = { navController.navigate(NavRouts.SearchProductScreen)})
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Placeholder text
                Text(
                    text = "Search for products, brands, and more",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 16.sp,
                    maxLines = 1,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

       /* // Filter Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "All Featured",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )

            Row {
                FilterChip(
                    selected = true,
                    onClick = {},
                    label = { Text("Sort") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.sort_card_icon),
                            contentDescription = "Sort"
                        )
                    },
                )

                Spacer(modifier = Modifier.width(8.dp))

                FilterChip(
                    selected = true,
                    onClick = {},
                    label = { Text("Filter") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_filter_alt_24),
                            contentDescription = "Filter"
                        )
                    },
                )
            }
        }*/
    }
}