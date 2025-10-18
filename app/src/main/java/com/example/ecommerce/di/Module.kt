package com.example.ecommerce.di

import android.content.Context
import com.example.ecommerce.data.local.preferencesDS.UserPreferencesDataStore
import com.example.ecommerce.data.remote.NetworkModule
import com.example.ecommerce.data.repository.FirebaseAuthRepository
import com.example.ecommerce.data.repository.LocalLocalProductRepo
import com.example.ecommerce.data.repository.ProductRepositoryImpl
import com.example.ecommerce.data.repository.UserPreferenceRepository
import com.example.ecommerce.domain.repo.ProductRepository
import com.example.ecommerce.domain.usecase.firebase_usecases.ForgetPasswordUseCase
import com.example.ecommerce.domain.usecase.preference_data_usecases.GetUserPreferencesUseCase
import com.example.ecommerce.domain.usecase.firebase_usecases.LoginUseCase
import com.example.ecommerce.domain.usecase.preference_data_usecases.SetUserPreferencesUseCase
import com.example.ecommerce.domain.usecase.firebase_usecases.SignUpUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module{

    @Provides
    @Singleton
    fun provideSomeString(): String {
        return "Hello from Hilt"
    }

    @Provides
    @Singleton
    fun providesAuth(): FirebaseAuth= FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(): FirebaseAuthRepository {
        return FirebaseAuthRepository()
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: FirebaseAuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }


    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: FirebaseAuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideForgetPasswordUseCase(authRepository: FirebaseAuthRepository): ForgetPasswordUseCase {
        return ForgetPasswordUseCase(authRepository)
    }

    //Preferences Data Store
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): UserPreferencesDataStore {
        return UserPreferencesDataStore(context)
    }

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(userPreferencesDataStore:UserPreferencesDataStore): UserPreferenceRepository {
        return UserPreferenceRepository(userPreferencesDataStore)
    }

    @Provides
    @Singleton
    fun provideGetUserPreferencesUseCase(userPreferenceRepository:UserPreferenceRepository): GetUserPreferencesUseCase {
        return GetUserPreferencesUseCase(userPreferenceRepository)
    }

    @Provides
    @Singleton
    fun provideSetUserPreferencesUseCase(userPreferenceRepository:UserPreferenceRepository): SetUserPreferencesUseCase {
        return SetUserPreferencesUseCase(userPreferenceRepository)
    }


    // Products

    @Provides
    @Singleton
    fun provideLocalProductRepository(userPreferenceRepository:UserPreferenceRepository): LocalLocalProductRepo {
        return LocalLocalProductRepo()
    }

    // Api Requirement

    @Provides
    @Singleton
    fun provideNetworkModule(): NetworkModule {
        return NetworkModule
    }
    @Provides
    @Singleton
    fun provideProductRepositoryImpl(networkModule:NetworkModule): ProductRepository {
        return ProductRepositoryImpl(networkModule)
    }




    }
