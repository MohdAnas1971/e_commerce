package com.store.ecommerceapplication.utils

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

object GoogleSignInHelper {
    
    private const val WEB_CLIENT_ID = "189946891949-roajsft2j3csmd78i6ip6mai5vf6vs5v.apps.googleusercontent.com"
    
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        Log.d("GoogleSignInHelper", "Creating GoogleSignInClient with web client ID: $WEB_CLIENT_ID")
        
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .build()
        
        return GoogleSignIn.getClient(context, gso)
    }
}
