package com.example.ecommerce.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.ecommerce.data.local.staticData.CategoryList
import com.example.ecommerce.data.local.staticData.SampleProductSamples
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val str: String
): ViewModel() {
    val categoryList= CategoryList
    val productList=SampleProductSamples
}

