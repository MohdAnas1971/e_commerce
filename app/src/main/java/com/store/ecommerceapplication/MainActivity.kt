package com.store.ecommerceapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.store.ecommerceapplication.Navigation.AppNavigation
import com.store.ecommerceapplication.ui.theme.EcommerceApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcommerceApplicationTheme {
                AppNavigation()
            }
        }
    }
}