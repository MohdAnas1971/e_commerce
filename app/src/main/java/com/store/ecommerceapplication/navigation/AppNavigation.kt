package com.store.ecommerceapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.store.ecommerceapplication.presentation.auth.ForgotPasswordScreen
import com.store.ecommerceapplication.presentation.auth.LoginScreen
import com.store.ecommerceapplication.presentation.auth.SignupScreen
import com.store.ecommerceapplication.presentation.cart.CartScreen
import com.store.ecommerceapplication.presentation.onboardingScreen.FristInterScreen
import com.store.ecommerceapplication.presentation.onboardingScreen.SecondInterScreen
import com.store.ecommerceapplication.presentation.onboardingScreen.ThirdInterScreen
import com.store.ecommerceapplication.presentation.products.ProductDetailScreen
import com.store.ecommerceapplication.presentation.products.ProductListScreen
import com.store.ecommerceapplication.presentation.splash.SplashScreen
import com.store.ecommerceapplication.presentation.userPreferences.UserPreferencesViewModel
import com.store.ecommerceapplication.presentation.wishlist.WishlistScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Inject ViewModels using Hilt
    val userPreferencesViewModel: UserPreferencesViewModel = viewModel()

    // Observe user preferences state
    val userPreferencesState by userPreferencesViewModel.state.collectAsState()

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {

        composable<Routes.LoginScreen> {
            LoginScreen(
               navHostController = navController
            )
        }

        composable<Routes.SignUpScreen> {
            SignupScreen(
                navHostController = navController
            )
        }

        composable<Routes.ForgetPasswordScreen> {
            ForgotPasswordScreen(navController)
        }

        composable<Routes.OnboardingScreen1> {
            FristInterScreen(navController)
        }

        composable<Routes.OnboardingScreen2> {
            SecondInterScreen(navController)
        }

        composable<Routes.OnboardingScreen3> {
            ThirdInterScreen(navController)
        }

        composable<Routes.ProductListScreen> {
            ProductListScreen(
                navController = navController
            )
        }

        composable<Routes.ProductDetailScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.ProductDetailScreen>() // it convert to object
            ProductDetailScreen(
                navController = navController,
                productId = args.productId
            )
        }

        composable<Routes.SplashScreen> {
            SplashScreen(
                onFinish = {
                    val destination = when {
                        userPreferencesState.isLoggedIn -> Routes.ProductListScreen
                        userPreferencesState.isFirstTimeLogin -> Routes.OnboardingScreen1
                        else -> Routes.LoginScreen
                    }

                    navController.navigate(destination) {
                        popUpTo(Routes.SplashScreen) { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.WishlistScreen> {
            WishlistScreen(
                navController = navController
            )
        }

        composable<Routes.CartScreen> {
            CartScreen(
                navController = navController
            )
        }
    }
}
