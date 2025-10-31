package com.store.ecommerceapplication.Presentation.UserPreferences

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.store.ecommerceapplication.domain.model.UserPreferencesState
import com.store.ecommerceapplication.domain.usecase.GetUserPreferencesUseCase
import com.store.ecommerceapplication.domain.usecase.SetUserPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val setUserPreferencesUseCase: SetUserPreferencesUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(UserPreferencesState())
    val state: StateFlow<UserPreferencesState> = _state.asStateFlow()
    
    init {
        observeUserPreferences()
    }
    
    private fun observeUserPreferences() {
        viewModelScope.launch {
            combine(
                getUserPreferencesUseCase.isFirstTimeLogin(), // false
                getUserPreferencesUseCase.isLoggedIn() // true
            ) { isFirstTime, isLoggedIn ->
                Log.d("com.store.ecommerceapplication.Presentation.UserPreferences.UserPreferencesViewModel", "DataStore values changed - isFirstTime: $isFirstTime, isLoggedIn: $isLoggedIn")
                UserPreferencesState(
                    isFirstTimeLogin = isFirstTime,
                    isLoggedIn = isLoggedIn,
                    isLoading = false
                )
            }.collect { newState ->
                Log.d("com.store.ecommerceapplication.Presentation.UserPreferences.UserPreferencesViewModel", "Updating state - isFirstTime: ${newState.isFirstTimeLogin}, isLoggedIn: ${newState.isLoggedIn}")
                _state.value = newState
            }
        }
    }

}
