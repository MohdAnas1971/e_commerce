package com.example.ecommerce.presentation.uiComponents

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.presentation.theme.Black

@Composable
fun HeadingText(text: String) {

    Text(text,
        fontSize = 36.sp,
        fontWeight = FontWeight(700),
        lineHeight =43.sp,
        color = Black,
        letterSpacing = 0.sp,
        modifier = Modifier.size(width = 192.dp,86.dp)
    )
}