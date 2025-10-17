package com.example.ecommerce.presentation.uiComponents.lazyLayout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ecommerce.data.local.staticData.ProductCategory


@Composable
 fun CircleLazyRow(categoryList: List<ProductCategory>) {

    Log.d("ui","CircleLazyRow")

    Row(
        modifier = Modifier.padding(5.dp)
    ){
        LazyRow {
            items(categoryList){item->

                ItemForLazyRow(item)
            }
        }
    }

}


@Composable
fun ItemForLazyRow(item: ProductCategory) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding( horizontal = 8.dp)
    ) {
        Card(
            modifier = Modifier.size(56.dp),
shape = CircleShape
        ){
            Image(
                painter = painterResource(item.imagRes),
                contentDescription = null,
               contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.height(3.dp))
        Text(item.name)
    }
}