package com.example.ecommerce.navigation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseClass : Application() { // Or whatever your Application class is named
    // ...
}