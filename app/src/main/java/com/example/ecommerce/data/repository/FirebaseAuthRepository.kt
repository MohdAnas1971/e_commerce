package com.example.ecommerce.data.repository

import com.example.ecommerce.domain.model.Result
import com.example.ecommerce.domain.repo.AuthRepo
import com.example.ecommerce.domain.repo.AuthResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepo {


    override suspend fun signup(email: String, password: String): Result<String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.Success("SignUp Successful")
        } catch (e: Exception) {
            return Result.Failure(e.message ?: "Error:  Unknown error found")
        }
    }
    override suspend fun login(email: String, password: String): Result<String> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success("Login Successful")
        } catch (e: Exception) {
            Result.Failure(e.message ?: "Error: Unknown error found")
        }
    }

    override fun signInWithGoogle(idToken: String, onResult: (AuthResultState) -> Unit) {
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

    override suspend fun forgetPassword(email: String): Result<String> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.Success("Send Email: Done")
        } catch (e: Exception) {
            Result.Failure(e.message ?: "Error: Unknown error found")
        }
    }

    override fun isLogin(): Boolean = auth.currentUser != null

    override fun hasAccount(email: String, onResult: (Boolean) -> Unit) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val methods = task.result?.signInMethods
                    onResult(!methods.isNullOrEmpty()) // true if an account exists
                } else {
                    onResult(false)
                }
            }
    }

}