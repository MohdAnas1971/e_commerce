package com.example.ecommerce.data.repository

import com.example.ecommerce.data.local.preferencesDS.UserPreferencesDataStore
import com.example.ecommerce.domain.repo.UserPreferencesRepo
import kotlinx.coroutines.flow.Flow

class UserPreferenceRepository(
    private val userPreferencesDataStore: UserPreferencesDataStore
): UserPreferencesRepo {
    override val isFirstTimeLogin: Flow<Boolean> = userPreferencesDataStore.isFirstTimeLogin

    override val isLoggedIn: Flow<Boolean> = userPreferencesDataStore.isLoggedIn
    override suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        userPreferencesDataStore.setFirstTimeLogin(isFirstTime)
    }
    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferencesDataStore.setLoggedIn(isLoggedIn)
    }


}