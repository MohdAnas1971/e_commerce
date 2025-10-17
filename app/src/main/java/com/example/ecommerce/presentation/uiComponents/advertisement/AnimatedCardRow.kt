package com.example.ecommerce.presentation.uiComponents.advertisement

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedCardRow() {

    Log.d("ui","AnimatedCardRow")
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(listState)
    val advertisementList = listOf(
        R.drawable.splash_get_started,
        R.drawable.splash_choose_product,
        R.drawable.splash_get_started,
        R.drawable.splash_make_pyment
    )

Row(horizontalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth().height(220.dp).padding(horizontal =16.dp)) {
    LazyRow(
        state = listState,
        flingBehavior = flingBehavior
    ){
        itemsIndexed(advertisementList){index,item->

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Card(modifier = Modifier.width(350.dp).height(190.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Image(painter = painterResource(item), contentDescription = null, contentScale = ContentScale.Crop)
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {

                    for (i in 1..advertisementList.size ) {

                        if (i==index+1) {
                            Image(painter = painterResource(R.drawable.pink_small_circle), contentDescription = null, modifier = Modifier.padding(2.dp))
                        }else{
                            Image(painter = painterResource(R.drawable.gray_small_circle), contentDescription = null,modifier = Modifier.padding(2.dp))

                        }
                    }
                }
            }

            Spacer(Modifier.width(16.dp))
        }
    }

}
}