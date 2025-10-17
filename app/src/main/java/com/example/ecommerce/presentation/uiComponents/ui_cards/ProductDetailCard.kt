package com.example.ecommerce.presentation.uiComponents.ui_cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import com.example.ecommerce.domain.model.ProductSample
import com.example.ecommerce.domain.model.Review
import com.example.ecommerce.presentation.theme.White


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailCard(
    imageHeight: Int =196,
    imagRes: Int = R.drawable.splash_choose_product,
    productSample: ProductSample = ProductSample("T-shirt","Product Description in tow line that ","2000", Review(738473L,4),R.drawable.casual_summer_t_shirt)
) {

    ElevatedCard(
        colors = CardDefaults.cardColors(
            White,
            disabledContentColor = White
        ),
        shape = RoundedCornerShape(12.dp),

        elevation = CardDefaults.elevatedCardElevation(
            16.dp,
            16.dp
        ),

        modifier= Modifier
        .width(170.dp),

        ) {
        Image(painter = painterResource(productSample.imageRes), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 170.dp, height = imageHeight.dp))
        Column(modifier= Modifier.fillMaxWidth().padding(7.dp)) {
            Text(productSample.name, maxLines = 1, style = TextStyle(fontWeight = FontWeight(500), fontSize = 16.sp))
            Spacer(Modifier.height(5.dp))

            Text(
                productSample.description,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 2
            )
            Spacer(Modifier.height(5.dp))

            Text(productSample.price, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(5.dp))

            StarRow(productSample.review)
        }
    }
}


@Composable
fun StarRow(review: Review = Review(30020L,3)) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            if (i <= review.reviewStar) {
                Image(painter = painterResource(R.drawable.colored_star),
                    contentDescription = null,
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.uncolored_star),
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.width(5.dp))
        Text(review.reviewNo.toString(), style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight(400), color = Color.Gray))
    }
}