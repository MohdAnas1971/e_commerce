package com.store.ecommerceapplication.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {


    @Serializable
    data object LoginScreen : Routes()

    @Serializable
    data object SignUpScreen : Routes()

    @Serializable
    data object ForgetPasswordScreen : Routes()

    @Serializable
    data object OnboardingScreen1 : Routes()


    @Serializable
    data object OnboardingScreen2 : Routes()


    @Serializable
    data object OnboardingScreen3 : Routes()


    @Serializable
    data object ProductListScreen : Routes()

    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data class ProductDetailScreen(val productId: Int) : Routes()

    @Serializable
    data object WishlistScreen : Routes()

    @Serializable
    data object CartScreen : Routes()
}