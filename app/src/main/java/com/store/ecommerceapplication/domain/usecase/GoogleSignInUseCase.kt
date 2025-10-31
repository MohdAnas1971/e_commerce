package com.store.ecommerceapplication.domain.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.store.ecommerceapplication.domain.repository.AuthRepository
import com.store.ecommerceapplication.domain.util.Result
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(account: GoogleSignInAccount): Result<String> {
        return repository.signInWithGoogle(account)
    }
}
