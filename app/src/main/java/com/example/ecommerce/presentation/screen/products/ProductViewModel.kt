package com.example.ecommerce.presentation.screen.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.domain.model.ResultIs
import com.example.ecommerce.domain.model.product_api_models.Product
import com.example.ecommerce.domain.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository : ProductRepository
): ViewModel(){

    private val _productState= MutableStateFlow < ResultIs<List<Product>>>(ResultIs.Idle)

    val productState=_productState.asStateFlow()

  /*  init {
        loadProducts()
    }*/

    fun loadProducts(){

        _productState.value= ResultIs.Loading
       viewModelScope.launch{
           try {
               val result=repository.getProducts()

               _productState.value= result
           }catch (e: Exception){

               _productState.value = ResultIs.Error(e.message ?: "Unknown Error ")
           }

       }
    }


    fun retryLoading(){
        loadProducts()
    }

}