package com.example.ecommerce.domain.usecase.firebase_usecases

import com.example.ecommerce.data.repository.FirebaseAuthRepository
import com.example.ecommerce.domain.model.Result
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<String> =authRepository.signup(email = email,password=password)
}