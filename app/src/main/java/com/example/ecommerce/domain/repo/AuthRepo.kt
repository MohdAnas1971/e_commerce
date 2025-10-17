package com.example.ecommerce.domain.repo


import com.example.ecommerce.domain.model.ResultIs

interface AuthRepo {
    suspend fun signUp(
        email: String,
        password: String,
    ): ResultIs<String>

    suspend fun login(
        email: String,
        password: String,
    ): ResultIs<String>

    fun loginWithGoogle(
        idToken: String,
        onResult: (AuthResultState) -> Unit,
    )

    suspend fun forgetPassword(
        email: String,
    ): ResultIs<String>

    fun isLogin(): Boolean

    fun hasAccount(
        email: String,
        onResult: (Boolean) -> Unit,
    )
}


sealed class AuthResultState {
    object Success : AuthResultState()
    data class Error(val message: String, val code: String? = null) : AuthResultState()
}
