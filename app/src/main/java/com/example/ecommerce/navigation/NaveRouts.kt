package com.example.ecommerce.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class NavRouts{

    //Splash /Onboarding /Get Started
    @Serializable
    object SplashScreen: NavRouts()

    @Serializable
    object OnBoardingScreen: NavRouts()

    @Serializable
    object GetStartedScreen: NavRouts()

    //Auth Screens
    @Serializable
    object LoginScreen: NavRouts()

    @Serializable
    object SignUpScreen: NavRouts()

    @Serializable
    object ForgotPasswordScreen: NavRouts()

    //Home Screens
    @Serializable
    object HomeScreen: NavRouts()

    //Product Screens
    @Serializable
    object SearchProductScreen: NavRouts()

    @Serializable
    object ProductListScreen: NavRouts()

    @Serializable
    data class ProductDetailScreen(val productId:Int) : NavRouts()

// Check Out Screen
    @Serializable
    object CheckoutScreen: NavRouts()

    @Serializable
    object PaymentScreen : NavRouts()

    @Serializable
    object ShoppingBagScreen: NavRouts()

    //Profile Screens
    @Serializable
    object UserProfileScreen: NavRouts()

}
