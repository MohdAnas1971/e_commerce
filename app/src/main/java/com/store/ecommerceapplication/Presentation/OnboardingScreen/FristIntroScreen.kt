package com.store.ecommerceapplication.Presentation.OnboardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.store.ecommerceapplication.Navigation.Routes
import com.store.ecommerceapplication.R

@Composable
fun FristInterScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Row with Skip and 1/3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "1/3",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Skip",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navHostController.navigate(Routes.LoginScreen)
                }
            )

        }

        // Spacer to push content to center
        Spacer(modifier = Modifier.weight(1f))

        // Image
        Image(
            painter = painterResource(R.drawable.eone),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(height = 350.dp, width = 350.dp)
        )

        // Spacer between image and title
        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Choose Products",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        // Spacer between title and description
        Spacer(modifier = Modifier.height(24.dp))

        // Description
        Text(
            text = "Discover a world of choices! Our app makes finding\n" +
                    "your perfect products simple and enjoyable.\n" +
                    "Browse effortlessly and pick what you love.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Spacer to push Next button to bottom
        Spacer(modifier = Modifier.weight(1f))

        // Bottom Row with Next button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Next",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navHostController.navigate(Routes.OnboardingScreen2)
                }
            )
        }
    }
}

