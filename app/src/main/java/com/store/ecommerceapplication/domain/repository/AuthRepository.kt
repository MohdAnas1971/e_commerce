package com.store.ecommerceapplication.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.store.ecommerceapplication.domain.util.Result

interface AuthRepository {

    suspend fun login(email:String,password:String): Result<String>
    suspend fun signup(email: String, password: String): Result<String>
    suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<String>

}