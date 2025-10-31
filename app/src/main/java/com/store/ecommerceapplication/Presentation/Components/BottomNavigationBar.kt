package com.store.ecommerceapplication.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.store.ecommerceapplication.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home : BottomNavItem("home", "Home", R.drawable.ic_home)
    data object Wishlist : BottomNavItem("wishlist", "Wishlist", R.drawable.ic_heart)
    data object Cart : BottomNavItem("cart", "Cart", R.drawable.ic_round_background_cart)
    data object Search : BottomNavItem("search", "Search", R.drawable.ic_search)
    data object Settings : BottomNavItem("settings", "Setting", R.drawable.ic_settings)
}

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit
) {
    val leftItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Wishlist
    )
    
    val rightItems = listOf(
        BottomNavItem.Search,
        BottomNavItem.Settings
    )

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Bottom navigation bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left items
            leftItems.forEach { item ->
                BottomNavItemView(
                    item = item,
                    isSelected = currentRoute == item.route,
                    onClick = { onItemClick(item) }
                )
            }
            
            // Empty space for floating cart button
            Box(modifier = Modifier.size(60.dp))
            
            // Right items
            rightItems.forEach { item ->
                BottomNavItemView(
                    item = item,
                    isSelected = currentRoute == item.route,
                    onClick = { onItemClick(item) }
                )
            }
        }
        
        // Floating cart button
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-20).dp)
                .size(56.dp)
                .shadow(8.dp, CircleShape)
                .background(Color.White, CircleShape)
                .clickable { onItemClick(BottomNavItem.Cart) }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_round_background_cart),
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconTint = if (isSelected) Color(0xFFF83758) else Color(0xFF666666)
    val textColor = if (isSelected) Color(0xFFF83758) else Color(0xFF666666)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp)
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            modifier = Modifier.size(24.dp),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconTint)
        )
        
        Text(
            text = item.title,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
