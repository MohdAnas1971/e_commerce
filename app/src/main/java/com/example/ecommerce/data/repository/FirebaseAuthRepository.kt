package com.example.ecommerce.data.repository

import androidx.compose.ui.geometry.Rect
import com.example.ecommerce.domain.repo.AuthRepo
import com.example.ecommerce.domain.repo.AuthResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import com.example.ecommerce.domain.model.ResultIs
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepo {


    override suspend fun signUp(
        email: String,
        password: String,
    ): ResultIs<String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            ResultIs.Success("SignUp Successful")
        }catch (e: Exception){
            return ResultIs.Error(e.message?:"Error:  Unknown error found")
        }
    }

    override suspend fun login(
        email: String,
        password: String,
    ): ResultIs<String> {
        return try {
            auth.signInWithEmailAndPassword(email,password).await()
            ResultIs.Success("Login Successful")
        }catch (e: Exception){
            ResultIs.Error(e.message?:"Error: Unknown error found")
        }
    }

    override fun loginWithGoogle(idToken: String, onResult: (AuthResultState) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(AuthResultState.Success)
                } else {
                    onResult(AuthResultState.Error(task.exception?.localizedMessage ?: "Google login failed"))
                }
            }
    }



    override suspend fun forgetPassword(email: String): ResultIs<String> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            ResultIs.Success("Send Email: Done")
        }catch (e: Exception){
            ResultIs.Error(e.message?:"Error: Unknown error found")
        }
    }

    override fun isLogin(): Boolean = auth.currentUser != null

    override fun hasAccount(email: String, onResult: (Boolean) -> Unit) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val methods = task.result?.signInMethods
                    onResult(!methods.isNullOrEmpty()) // true if account exists
                } else {
                    onResult(false)
                }
            }
    }
}