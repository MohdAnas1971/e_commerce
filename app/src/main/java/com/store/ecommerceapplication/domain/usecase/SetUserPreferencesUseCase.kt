package com.store.ecommerceapplication.domain.usecase

import com.store.ecommerceapplication.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class SetUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    
    suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        userPreferencesRepository.setFirstTimeLogin(isFirstTime)
    }
    
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        userPreferencesRepository.setLoggedIn(isLoggedIn)
    }
}
