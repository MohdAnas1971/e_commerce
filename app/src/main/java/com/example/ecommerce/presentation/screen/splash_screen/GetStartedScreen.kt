package com.example.ecommerce.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.NavRouts
import com.example.ecommerce.presentation.uiComponents.CustomSubmitButton


@Composable
fun GetStartedScreen(navController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.background_image_get_started),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(40.dp).padding(bottom = 50.dp)
        ) {
            Text(
                "You want Authentic, here you go!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
            CustomSubmitButton(
                "Get Started",
            ) { navController.navigate(NavRouts.LoginScreen) }
        }
    }
}


