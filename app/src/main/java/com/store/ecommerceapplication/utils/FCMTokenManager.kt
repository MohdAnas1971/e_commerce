package com.store.ecommerceapplication.utils

import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

class FCMTokenManager(private val context: Context) {
    
    companion object {
        private const val TAG = "FCMTokenManager"
        private const val PREFS_NAME = "fcm_prefs"
        private const val TOKEN_KEY = "fcm_token"
    }
    
    private val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    suspend fun getToken(): String? {
        return try {
            val token = FirebaseMessaging.getInstance().token.await()
            Log.d(TAG, "FCM Token retrieved: $token")
            
            // Save token to SharedPreferences
            saveToken(token)
            
            token
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get FCM token", e)
            null
        }
    }
    
    fun getSavedToken(): String? {
        return sharedPrefs.getString(TOKEN_KEY, null)
    }
    
    private fun saveToken(token: String) {
        sharedPrefs.edit()
            .putString(TOKEN_KEY, token)
            .apply()
        Log.d(TAG, "FCM Token saved to SharedPreferences")
    }
    
    fun clearToken() {
        sharedPrefs.edit()
            .remove(TOKEN_KEY)
            .apply()
        Log.d(TAG, "FCM Token cleared from SharedPreferences")
    }
    
    suspend fun refreshToken(): String? {
        return try {
            FirebaseMessaging.getInstance().deleteToken().await()
            getToken()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to refresh FCM token", e)
            null
        }
    }
}
