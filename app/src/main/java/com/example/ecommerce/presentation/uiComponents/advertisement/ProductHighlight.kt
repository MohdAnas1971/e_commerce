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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.PinkDark
import com.example.ecommerce.presentation.theme.White

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductHighlight(modifier: Modifier = Modifier) {

    Log.d("ui","ProductHighlight")

    Card(
        modifier= Modifier.height(150.dp),
        shape = RoundedCornerShape(8.dp)
    ){
        Box(Modifier.fillMaxSize()){
            Image(painter = painterResource(R.drawable.backgroud_highlight), contentDescription = null)
            Image(painter = painterResource(R.drawable.background_yello_line), contentDescription = null)

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier= Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 8.dp)
            ){
                Image(painter = painterResource(R.drawable.product_highlight), contentDescription = null)

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Text("Flat and Heels", style = MaterialTheme.typography.titleLarge)
                    Text("Stand a chance to get rewarded")

                    Row (
                        horizontalArrangement = Arrangement.End,
                        modifier=Modifier
                    ){
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

        }


    }

}