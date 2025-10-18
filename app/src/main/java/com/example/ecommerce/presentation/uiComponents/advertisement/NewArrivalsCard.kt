package com.example.ecommerce.presentation.uiComponents.advertisement

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.PinkDark
import com.example.ecommerce.presentation.theme.White


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewArrivalsCard(
    imagRes:Int=R.drawable.summer_sale_arrival,
    arrivalsName: String ="Summer'",
    collectionCount: Int = 25
) {

    Log.d("ui","NewArrivalsCard")

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = White,
            disabledContentColor = White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier= Modifier.fillMaxWidth(),
        ) {
        Image(
            painter = painterResource(imagRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text("New Arrivals", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(5.dp))
                Text("$arrivalsName $collectionCount Collections", style = MaterialTheme.typography.titleMedium)
            }

            Button(
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = PinkDark,
                    contentColor = White
                ),
                shape = RoundedCornerShape(6.dp)

            ) {
                Text("View all", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.width(5.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}
