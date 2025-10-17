package com.example.ecommerce.presentation.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.PinkDark


@Composable
fun ContinueWithAccount() {
    
    val continueWithGoogle = {}
    val continueWithFacebook = {}
    val continueWithApple = {}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("-OR Continue with -",style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight(500), color = Color.Gray))
        Spacer(Modifier.height(30.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.google), contentDescription ="Sign UP with ", modifier = Modifier.size(54.dp).clickable(onClick = continueWithGoogle))
            Spacer(Modifier.width(16.dp))
            Image(painter = painterResource(R.drawable.apple), contentDescription ="Sign UP with ", modifier = Modifier.size(54.dp).clickable(onClick = {continueWithFacebook()}))
            Spacer(Modifier.width(16.dp))
            Image(painter = painterResource(R.drawable.facebook), contentDescription ="Sign UP with ", modifier = Modifier.size(54.dp).clickable(onClick = {continueWithApple()}))
        }

        Spacer(Modifier.height(20.dp))

    }
        }


