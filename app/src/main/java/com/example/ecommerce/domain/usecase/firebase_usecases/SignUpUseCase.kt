package com.example.ecommerce.domain.usecase.firebase_usecases

import com.example.ecommerce.data.repository.FirebaseAuthRepository
import com.example.ecommerce.domain.model.ResultIs
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): ResultIs<String> =authRepository.signUp(email = email,password=password)
}