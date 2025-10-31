package com.store.ecommerceapplication.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.store.ecommerceapplication.navigation.Routes

@Composable
fun ForgotPasswordScreen(navHostController: NavHostController) {
    var userForgotEmail by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                "Forgot\n\npassword?",
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 15.dp),
                letterSpacing = 0.5.sp
            )
        }

        // Middle Section - Input Fields and Login Button
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Username/Email Input Field
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .border(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFB2B2B2),
                        width = 2.dp
                    )
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFFF3F3F3))
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email icon",
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterStart)
                        .size(25.dp)
                )
                TextField(
                    value = userForgotEmail,
                    onValueChange = { userForgotEmail = it },
                    modifier = Modifier
                        .padding(start = 45.dp, end = 45.dp)
                        .fillMaxSize(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Enter your e-mail address ",
                            fontSize = 14.sp
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        letterSpacing = 0.5.sp,
                        color = Color.Black
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = "we will send you a message to set or reset your new password",
                fontSize = 14.sp,
                color = Color.Gray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF8817C6))
                    .clickable {
                        navHostController.navigate(Routes.LoginScreen)
                    }
            ) {
                Text(
                    text = "Submit",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.Center),
                    letterSpacing = 0.5.sp
                )
            }
            Spacer(modifier = Modifier.height(400.dp))


        }
    }
}
