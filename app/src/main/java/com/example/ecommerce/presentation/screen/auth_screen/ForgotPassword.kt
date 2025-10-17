package com.example.ecommerce.presentation.screen.auth_screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.PinkDark
import com.example.ecommerce.presentation.uiComponents.CustomInputField
import com.example.ecommerce.presentation.uiComponents.CustomSubmitButton
import com.example.ecommerce.presentation.uiComponents.HeadingText


@Composable
fun ForgotPasswordScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val spacerHeight=30.dp
    var isLoading by remember { mutableStateOf(false) }

    Scaffold { innerPadding->
        Column(
            // horizontalAlignment = Alignment.TopStart,
            // verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(spacerHeight))
            HeadingText("Forgot password?")
            Spacer(modifier = Modifier.height(spacerHeight))
            CustomInputField("User Name or Email",{}, R.drawable.baseline_email_24,"EnterUserName")
            Spacer(modifier = Modifier.height(spacerHeight))
            Text(
                text =buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight(400), fontSize = 12.sp, color = PinkDark)
                    ){
                        append("*")
                    }
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight(400), fontSize = 12.sp, color = Color.Gray)
                    ){
                        append("We will send you a message to set or reset your new password")
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomSubmitButton("Submit", isLoading) {

            }

        }
    }
}

