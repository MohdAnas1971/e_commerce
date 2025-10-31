package com.store.ecommerceapplication.data.repositoryimpl

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.store.ecommerceapplication.domain.repository.AuthRepository
import com.store.ecommerceapplication.domain.util.Result
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success("Login successful") // manual data emit kart hai Result Class ma
        } catch (e: Exception) {
            Result.Failure(e.localizedMessage ?: "Unknown error during login")
        }
    }

    override suspend fun signup(email: String, password: String): Result<String> {
        return try {
            Log.d("AuthRepositoryImpl", "Starting signup for: $email")
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Log.d("AuthRepositoryImpl", "Account created successfully, current user: ${firebaseAuth.currentUser?.email}")
            
            // Sign out the user immediately after account creation
            // User should manually log in after registration
            firebaseAuth.signOut()
            Log.d("AuthRepositoryImpl", "User signed out after signup, current user: ${firebaseAuth.currentUser}")
            
            Result.Success("Signup successful")
        } catch (e: Exception) {
            Log.e("AuthRepositoryImpl", "Signup failed: ${e.message}", e)
            Result.Failure(e.localizedMessage ?: "Unknown error during signup")
        }
    }

    override suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<String> {
        return try {
            Log.d("AuthRepositoryImpl", "Starting Google sign-in with account: ${account.email}")
            Log.d("AuthRepositoryImpl", "ID Token available: ${account.idToken != null}")
            
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            Log.d("AuthRepositoryImpl", "Created credential, signing in with Firebase")
            
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            Log.d("AuthRepositoryImpl", "Firebase sign-in successful. User: ${authResult.user?.email}")
            
            Result.Success("Google sign-in successful")
        } catch (e: Exception) {
            Log.e("AuthRepositoryImpl", "Google sign-in failed: ${e.message}", e)
            Result.Failure(e.localizedMessage ?: "Unknown error during Google sign-in")
        }
    }
}
