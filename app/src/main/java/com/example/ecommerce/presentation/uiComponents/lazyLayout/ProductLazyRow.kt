package com.example.ecommerce.presentation.uiComponents.lazyLayout

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerce.domain.model.ProductSample
import com.example.ecommerce.presentation.uiComponents.ui_cards.ProductDetailCard

@Composable
fun ProductLazyRow(productSampleList: List<ProductSample>, imageHeight:Int) {

    Log.d("ui","ProductLazyRow")

    Row(
        modifier = Modifier.padding(5.dp)
    ){
        LazyRow {
            items(productSampleList){ item->
                ProductDetailCard(productSample = item, imageHeight = imageHeight)
                Spacer(Modifier.width(16.dp))
            }
        }
    }

}