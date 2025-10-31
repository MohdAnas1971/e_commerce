package com.example.ecommerce.domain.repo


import com.example.ecommerce.domain.model.Result

interface AuthRepo {
    suspend fun signup(
        email: String,
        password: String,
    ): Result<String>

    suspend fun login(
        email: String,
        password: String,
    ): Result<String>

    fun signInWithGoogle(
        idToken: String,
        onResult: (AuthResultState) -> Unit,
    )

    suspend fun forgetPassword(
        email: String,
    ): Result<String>

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
