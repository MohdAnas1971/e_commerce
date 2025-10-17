package com.example.ecommerce.presentation.uiComponents.advertisement

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.White


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpecialOffersCard (modifier: Modifier = Modifier) {


    Log.d("ui","SpecialOffersCard")

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )

    ){
        Row(Modifier.fillMaxWidth()
            .height(86.dp)
            .padding(8.dp)
        ) {
            Image(painter = painterResource(R.drawable.spacial_offers),
                contentDescription = null,
                modifier= Modifier.clip(shape = CircleShape)
                )
            Spacer(Modifier.width(14.dp))

            Column {
                Text("Special Offers " ,
                    style = MaterialTheme.typography.titleLarge)
                Text("We make sure you get the offer you need at best prices",
                    maxLines = 2,
                    lineHeight = 20.sp,
                    style = MaterialTheme.typography.titleMedium, color = Color.Gray)
            }
        }
    }

}