package com.store.ecommerceapplication.domain.util

// Ye base class hai, jiske andar hum 4 type ke result define kar rahe hain.
sealed class Result< out T> {

    data object Idle : Result<Nothing>()  // Idle – Kuch hua hi nahi ab tak
    data object Loading : Result<Nothing>() //  Operation chal raha hai (jaise API request ja rahi hai)
    data class Success<T>(val data: T) : Result<T>() // Operation success ho gaya aur hume data mila
    data class Failure(val message:String):Result<Nothing>() //  Operation fail ho gaya, aur hume error message mila

}

/*

data class Success<T>(val data: T) : Result<T>()

Jab kaam success ho gaya ho aur aapko result mila ho —
T kisi bhi type ka ho sakta hai (String, User, List<Product>, etc.)



 */