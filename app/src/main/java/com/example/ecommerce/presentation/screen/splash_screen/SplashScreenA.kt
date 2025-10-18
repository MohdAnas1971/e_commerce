package com.example.ecommerce.presentation.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.NavRouts
import com.example.ecommerce.presentation.userPreferencesDataStore.UserPreferencesViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreenA (
    navController: NavHostController,
    userPreferencesViewModel: UserPreferencesViewModel
) {

    val userPState by userPreferencesViewModel.state.collectAsState()
    var nextScreen by remember { mutableStateOf<NavRouts>(NavRouts.SplashScreen) }

    LaunchedEffect(Unit) {
        delay(3000)

        nextScreen = if (userPState.isLoggedIn){
            NavRouts.ProductListScreen
        }else{
            if (userPState.isFirstTimeLogin){
                NavRouts.OnBoardingScreen
            }else{
                NavRouts.LoginScreen
            }
        }
        navController.navigate(nextScreen){
            popUpTo(NavRouts.SplashScreen) { inclusive=true  }
        }
    }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ){
            Image(
                painter = painterResource(R.drawable.stylish_app_logo),
                contentDescription =null,
            )
        }
}