package com.example.ecommerce.domain.usecase.firebase_usecases

import com.example.ecommerce.data.repository.FirebaseAuthRepository
import com.example.ecommerce.domain.model.ResultIs
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) {

    suspend operator fun invoke(email: String): ResultIs<String> =authRepository.forgetPassword(email)
}