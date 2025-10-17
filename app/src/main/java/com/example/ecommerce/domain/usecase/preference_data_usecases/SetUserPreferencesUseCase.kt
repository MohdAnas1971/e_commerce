package com.example.ecommerce.domain.usecase.preference_data_usecases

import com.example.ecommerce.data.repository.UserPreferenceRepository

class SetUserPreferencesUseCase(
    private val userPreferenceRepository: UserPreferenceRepository,
) {
    suspend fun setIsFirstTimeLogin(isFirstTimeLogin: Boolean) = userPreferenceRepository.setFirstTimeLogin(isFirstTimeLogin)

    suspend fun setLoggedIn(isLoggedIn: Boolean) = userPreferenceRepository.setLoggedIn(isLoggedIn)
}