package com.example.ecommerce.presentation.screen.auth_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.domain.model.Result
import com.example.ecommerce.navigation.NavRouts
import com.example.ecommerce.presentation.theme.PinkDark
import com.example.ecommerce.presentation.uiComponents.*


@Composable
fun SignUpScreen(navController: NavHostController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    var conformPassWord by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context= LocalContext.current
    val authState by authViewModel.authState.collectAsState()

    val spacerHeight = 30.dp


    LaunchedEffect(authState) {
        if(authState!= Result.Loading){
            isLoading=false
        }
        when (val currentState = authState) {
            is Result.Failure -> {
                Toast.makeText(
                    context,
                    "Error:${currentState.message} ❌ SignUp Failed",
                    Toast.LENGTH_SHORT
                ).show()            }
            Result.Idle -> {}
            Result.Loading -> {
                isLoading = true
            }
            is Result.Success<*> -> {
                    Toast.makeText(
                        context,
                        "✅ SignUp Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                navController.navigate(NavRouts.LoginScreen) {
                    popUpTo(NavRouts.LoginScreen) { inclusive = true }
                }
                authViewModel.resetAuthState()
            }
        }
    }



    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(spacerHeight))
            HeadingText("Create an account")
            Spacer(modifier = Modifier.height(spacerHeight))
            CustomInputField(
                email,
                {email=it},
                R.drawable.outline_person_24,
                "EnterUserName"
            )
            Spacer(modifier = Modifier.height(spacerHeight))

            CustomPasswordField(passWord, {passWord=it})
            Spacer(modifier = Modifier.height(spacerHeight))

            CustomPasswordField(conformPassWord, {conformPassWord=it}, placeHolder = "ConfirmPassword")
            Spacer(modifier = Modifier.height(spacerHeight-20.dp))

            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    ) {
                        append("By clicking the ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            color = PinkDark
                        )
                    ) {
                        append("Register ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(400),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    ) {
                        append("button, you agree to the public offer ")
                    }
                }
            )
            Spacer(modifier = Modifier.height(spacerHeight))
            CustomSubmitButton("Register", isLoading) {
                if (email.isNotEmpty() && passWord.isNotEmpty()) {
                    if (passWord == conformPassWord) {
                        authViewModel.signUp(email, passWord)
                    } else {
                        Toast.makeText(
                            context,
                            "Both password fields should be the same.",
                            Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Email and PassWord Can't be Empty", Toast.LENGTH_LONG).show()
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
            ContinueWithAccount()

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight(500), color = Color.Gray)){
                            append("I Already Have an Account ")
                        }
                        withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight(600), textDecoration = TextDecoration.Underline , color = PinkDark)){
                            append("Login")
                        }
                    },
                    modifier = Modifier.clickable(onClick = {navController.navigate(NavRouts.LoginScreen)})
                )
            }
        }
    }
}

