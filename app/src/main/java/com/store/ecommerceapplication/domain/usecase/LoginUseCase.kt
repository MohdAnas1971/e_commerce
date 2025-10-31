package com.store.ecommerceapplication.domain.usecase

import com.store.ecommerceapplication.domain.repository.AuthRepository
import com.store.ecommerceapplication.domain.util.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository:AuthRepository) {

    suspend operator fun invoke(email:String,password:String):Result<String>{

        return  repository.login(email,password)
    }
}