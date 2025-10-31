package com.store.ecommerceapplication.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.store.ecommerceapplication.data.local.CartDataStore
import com.store.ecommerceapplication.data.local.UserPreferencesDataStore
import com.store.ecommerceapplication.data.local.dao.WishlistDao
import com.store.ecommerceapplication.data.local.database.EcoMartDatabase
import com.store.ecommerceapplication.data.remote.ProductApiService
import com.store.ecommerceapplication.data.repositoryimpl.AuthRepositoryImpl
import com.store.ecommerceapplication.data.repositoryimpl.CartRepositoryImpl
import com.store.ecommerceapplication.data.repositoryimpl.ProductRepositoryImpl
import com.store.ecommerceapplication.data.repositoryimpl.UserPreferencesRepositoryImpl
import com.store.ecommerceapplication.data.repositoryimpl.WishlistRepositoryImpl
import com.store.ecommerceapplication.domain.repository.AuthRepository
import com.store.ecommerceapplication.domain.repository.CartRepository
import com.store.ecommerceapplication.domain.repository.ProductRepository
import com.store.ecommerceapplication.domain.repository.UserPreferencesRepository
import com.store.ecommerceapplication.domain.repository.WishlistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): UserPreferencesDataStore {
        return UserPreferencesDataStore(context)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            defaultRequest {
                url("https://dummyjson.com/")
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }

            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideProductApiService(httpClient: HttpClient): ProductApiService {
        return ProductApiService(httpClient)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApiService: ProductApiService): ProductRepository {
        return ProductRepositoryImpl(productApiService)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(userPreferencesDataStore: UserPreferencesDataStore): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(userPreferencesDataStore)
    }

    @Provides
    @Singleton
    fun provideEcoMartDatabase(@ApplicationContext context: Context): EcoMartDatabase {
        return Room.databaseBuilder(
            context,
            EcoMartDatabase::class.java,
            "ecomart_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWishlistDao(database: EcoMartDatabase): WishlistDao {
        return database.wishlistDao()
    }

    @Provides
    @Singleton
    fun provideWishlistRepository(wishlistDao: WishlistDao): WishlistRepository {
        return WishlistRepositoryImpl(wishlistDao)
    }

    @Provides
    @Singleton
    fun provideCartDataStore(@ApplicationContext context: Context): CartDataStore {
        return CartDataStore(context)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDataStore: CartDataStore): CartRepository {
        return CartRepositoryImpl(cartDataStore)
    }
}
