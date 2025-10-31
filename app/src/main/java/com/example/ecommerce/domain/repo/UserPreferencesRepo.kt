package com.example.ecommerce.domain.repo

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepo {

    val isFirstTimeLogin: Flow<Boolean?>

    val isLoggedIn: Flow<Boolean>

    suspend fun setFirstTimeLogin(isFirstTime: Boolean)

    suspend fun setLoggedIn(isLoggedIn: Boolean)
}