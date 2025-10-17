package com.example.ecommerce.presentation.uiComponents.advertisement

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.Pink85
import com.example.ecommerce.presentation.theme.White
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TrendingProductCard(lastDate: String = "29/02/26", containerColors: Color= Pink85) {

    Log.d("ui","TrendingProductCard")

    val remainingTime by remember { mutableStateOf(LocalTime.now()) }

    val formatter = DateTimeFormatter.ofPattern("hh,mm,ss")

    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColors,
            contentColor = White
        ),
        shape = RoundedCornerShape(6.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp)
        ) {
            Column {
                Text("Trending Products", style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_date_range_24),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("Last Date $lastDate")
                }

            }

            OutlinedButton(
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = White
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("View all", style = MaterialTheme.typography.labelLarge)
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }

    }


}

