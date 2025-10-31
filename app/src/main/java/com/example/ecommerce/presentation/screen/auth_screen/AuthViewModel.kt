package com.example.ecommerce.presentation.screen.auth_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.data.repository.FirebaseAuthRepository
import com.example.ecommerce.domain.model.Result
import com.example.ecommerce.domain.usecase.firebase_usecases.ForgetPasswordUseCase
import com.example.ecommerce.domain.usecase.firebase_usecases.LoginUseCase
import com.example.ecommerce.domain.usecase.firebase_usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
private val authRepository: FirebaseAuthRepository,
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val forgetPasswordUseCase: ForgetPasswordUseCase
) : ViewModel() {

    private val _authState: MutableStateFlow<Result<String>> = MutableStateFlow(Result.Idle)
    val authState=_authState.asStateFlow()

    fun login(email: String, password: String){

        _authState.value= Result.Loading
        viewModelScope.launch {
            val result= loginUseCase(email,password)
            _authState.value=result
        }
    }

    fun signUp(email: String,password: String){

        _authState.value= Result.Loading

        viewModelScope.launch {
            val result=signUpUseCase(email,password)
            _authState.value=result
        }
    }

    fun signUpWithGoogle(){

    }

    fun forgetPassword(email: String){
        _authState.value= Result.Loading

        viewModelScope.launch {
           val result= forgetPasswordUseCase(email)
            _authState.value=result
        }
    }


    fun resetAuthState(){
        _authState.value= Result.Idle
    }


}
