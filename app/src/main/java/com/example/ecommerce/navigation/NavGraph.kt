package com.example.ecommerce.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ecommerce.presentation.screen.splash_screen.GetStartedScreen
import com.example.ecommerce.presentation.screen.products.ProductDetailScreen
import com.example.ecommerce.presentation.screen.products.SearchProductScreen
import com.example.ecommerce.presentation.screen.auth_screen.AuthViewModel
import com.example.ecommerce.presentation.screen.auth_screen.ForgotPasswordScreen
import com.example.ecommerce.presentation.screen.auth_screen.LoginScreen
import com.example.ecommerce.presentation.screen.auth_screen.SignUpScreen
import com.example.ecommerce.presentation.screen.checkOut.CheckoutScreen
import com.example.ecommerce.presentation.screen.checkOut.PaymentScreen
import com.example.ecommerce.presentation.screen.checkOut.ShoppingBagScreen
import com.example.ecommerce.presentation.screen.home.HomeScreen
import com.example.ecommerce.presentation.screen.products.ProductListScreen
import com.example.ecommerce.presentation.screen.profile.UserProfileScreen
import com.example.ecommerce.presentation.screen.splash_screen.OnBoardingScreen
import com.example.ecommerce.presentation.screen.splash_screen.SplashScreenA
import com.example.ecommerce.presentation.userPreferencesDataStore.UserPreferencesViewModel


@Composable
fun NavGraph(
    authViewModel: AuthViewModel = hiltViewModel(),
    userPreferencesViewModel: UserPreferencesViewModel = hiltViewModel(),
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = NavRouts.SplashScreen
    ) {

        //Splash screen
        composable<NavRouts.SplashScreen> {
            SplashScreenA(navController, userPreferencesViewModel)
        }

        composable<NavRouts.OnBoardingScreen> {
            OnBoardingScreen(navController, userPreferencesViewModel)
        }

        composable<NavRouts.GetStartedScreen> {
            GetStartedScreen(navController)
        }

        // Auth Screen
        composable<NavRouts.LoginScreen> {
            LoginScreen(navController, authViewModel, userPreferencesViewModel)
        }

        composable<NavRouts.SignUpScreen> {
            SignUpScreen(navController, authViewModel)
        }

        composable<NavRouts.ForgotPasswordScreen> {
            ForgotPasswordScreen(navController, authViewModel)
        }

        // Home Screens
        composable<NavRouts.HomeScreen> {
            HomeScreen(navController = navController)
        }
        //Product  Screens
        composable<NavRouts.SearchProductScreen> {
            SearchProductScreen(navController = navController)
        }
        composable<NavRouts.ProductDetailScreen> {backStackEntry->
            val arg=backStackEntry.toRoute<NavRouts.ProductDetailScreen>() //fetch and convert to ProductDetailScreen object
            ProductDetailScreen(navController,arg.productId)
        }
        composable<NavRouts.ProductListScreen> {
            ProductListScreen(navController = navController)
        }

        //Check Out Screens
        composable<NavRouts.CheckoutScreen> {
            CheckoutScreen()
        }
        composable<NavRouts.ShoppingBagScreen> {
            ShoppingBagScreen()
        }
        composable<NavRouts.PaymentScreen> {
            PaymentScreen()
        }
       //Profile Screens
        composable<NavRouts.UserProfileScreen> {
            UserProfileScreen()
        }
    }

}

