package com.example.ecommerce.presentation.userPreferencesDataStore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.model.UserPreferencesState
import com.example.ecommerce.domain.usecase.preference_data_usecases.GetUserPreferencesUseCase
import com.example.ecommerce.domain.usecase.preference_data_usecases.SetUserPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
    private val setUserPreferencesUseCase: SetUserPreferencesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UserPreferencesState())
    val state = _state.asStateFlow()

    init {
        Log.d("checkFirst","UPViewmodel")
        observeUserPreferences()
    }

    private fun observeUserPreferences(){

        viewModelScope.launch {
            combine(
                getUserPreferencesUseCase.isFirstTimeLogin(),
                getUserPreferencesUseCase.isLoggedIn(),
            ){isFirstTime,isLoggedIn->
                UserPreferencesState(
                    isFirstTimeLogin =isFirstTime,
                    isLoggedIn=isLoggedIn,
                    isLoading = false
                )
            }.collect {newState ->
                _state.value=newState
            }
        }
    }

    fun setFirstTimeLogin(isFirstTimeLogin: Boolean) {
        viewModelScope.launch {
            setUserPreferencesUseCase.setIsFirstTimeLogin(isFirstTimeLogin)
        }
    }


    fun setLoggedIn(isLoggedIn: Boolean) {
        Log.d("UserPreferencesViewModel","BeforeOut: ${_state.value.isLoggedIn}")
        viewModelScope.launch {
            Log.d("UserPreferencesViewModel","BeforeIn: ${_state.value.isLoggedIn}")
            setUserPreferencesUseCase.setLoggedIn(isLoggedIn)
            Log.d("UserPreferencesViewModel","AfterIn: ${_state.value.isLoggedIn}")
        }
        Log.d("UserPreferencesViewModel","AfterOut: ${_state.value.isLoggedIn}")
    }

}