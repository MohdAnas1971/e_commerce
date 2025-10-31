package com.example.ecommerce.presentation.screen.check_out_screen





import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Checkout",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { /* Handle back navigation */ }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Order Summary
            OrderSummary()

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Methods
            PaymentMethods()

            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            ContinueButton()
        }
    }
}

@Composable
fun OrderSummary() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Order Summary",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        OrderDetailRow("Order", "₹ 7,000")
        OrderDetailRow("Shipping", "₹ 30")

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp),
            thickness = DividerDefaults.Thickness, color = Color.LightGray
        )

        OrderDetailRow("Total", "₹ 7,030", isTotal = true)
    }
}

@Composable
fun OrderDetailRow(label: String, value: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (isTotal) {
                MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            } else {
                MaterialTheme.typography.bodyMedium
            }
        )
        Text(
            text = value,
            style = if (isTotal) {
                MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            } else {
                MaterialTheme.typography.bodyMedium
            }
        )
    }
}

@Composable
fun PaymentMethods() {
    var selectedMethod by  remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Payment",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // VISA Card
        PaymentMethodCard(
            title = "VISA",
            cardNumber = "**** **** **** 209",
            isSelected = selectedMethod == 0,
            onClick = { selectedMethod = 0 }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // PayPal
        PaymentMethodCard(
            title = "PayPal",
            cardNumber = "**** *** 2109",
            isSelected = selectedMethod == 1,
            onClick = { selectedMethod = 1 }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Another card
        PaymentMethodCard(
            title = "",
            cardNumber = "**** **** **** 2109",
            isSelected = selectedMethod == 2,
            onClick = { selectedMethod = 2 }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Another card
        PaymentMethodCard(
            title = "",
            cardNumber = "**** *** 2109",
            isSelected = selectedMethod == 3,
            onClick = { selectedMethod = 3 }
        )
    }
}

@Composable
fun PaymentMethodCard(title: String, cardNumber: String, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Selection indicator
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(2.dp, borderColor, CircleShape)
                .background(
                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                    CircleShape
                )
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Card details
        Column {
            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Text(
                text = cardNumber,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ContinueButton() {
    Button(
        onClick = { /* Handle continue action */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "Continue",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(icon = Icons.Default.Home, label = "Home")
        BottomNavItem(icon = Icons.Default.Favorite, label = "Wishlist")
        BottomNavItem(icon = Icons.Default.Search, label = "Search")
        BottomNavItem(icon = Icons.Default.Settings, label = "Setting")
    }
}

@Composable
fun BottomNavItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { /* Handle navigation */ }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PaymentScreen()
        }
    }
}