package com.example.ecommerce.domain.usecase.preference_data_usecases

import com.example.ecommerce.data.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetUserPreferencesUseCase(
    private val userPreferenceRepository: UserPreferenceRepository
){
    fun isFirstTimeLogin(): Flow<Boolean> =userPreferenceRepository.isFirstTimeLogin

    fun isLoggedIn(): Flow<Boolean> =userPreferenceRepository.isLoggedIn
}