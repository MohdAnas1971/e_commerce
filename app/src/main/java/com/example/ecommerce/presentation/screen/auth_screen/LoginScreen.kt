package com.example.ecommerce.presentation.screen.auth_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
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
import com.example.ecommerce.presentation.uiComponents.ContinueWithAccount
import com.example.ecommerce.presentation.uiComponents.CustomInputField
import com.example.ecommerce.presentation.uiComponents.CustomPasswordField
import com.example.ecommerce.presentation.uiComponents.CustomSubmitButton
import com.example.ecommerce.presentation.uiComponents.HeadingText
import com.example.ecommerce.presentation.userPreferencesDataStore.UserPreferencesViewModel


@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    userPreferencesViewModel: UserPreferencesViewModel
) {
    val spacerHeight = 30.dp

    var email by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }

    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(authState) {
        if(authState!= Result.Loading){
            isLoading=false
        }
        when (val currentState = authState) {
            is Result.Failure -> {
                Toast.makeText(context, currentState.message, Toast.LENGTH_LONG).show()
            }
            Result.Idle -> {}
            Result.Loading -> {
                isLoading = true
            }
            is Result.Success<*> -> {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(NavRouts.ProductListScreen) {
                    popUpTo(NavRouts.LoginScreen) { inclusive = true }
                }
                userPreferencesViewModel.setLoggedIn(true)
                userPreferencesViewModel.setFirstTimeLogin(false)
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
            HeadingText("Welcome Back!")
            Spacer(modifier = Modifier.height(spacerHeight))
            CustomInputField(email, { email = it }, R.drawable.outline_person_24, "EnterUserName")
            Spacer(modifier = Modifier.height(spacerHeight))
            CustomPasswordField(passWord, { passWord = it })
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "Forgot Password?", style = TextStyle(
                    fontWeight = FontWeight(400), fontSize = 12.sp, color = PinkDark
                ), modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate(
                            NavRouts.ForgotPasswordScreen
                        )
                    }))
            }
            Spacer(modifier = Modifier.height(60.dp))
            CustomSubmitButton("Login",isLoading) {

                if (email.isNotEmpty() && passWord.isNotEmpty()) {
                    authViewModel.login(email, passWord)
                } else {
                    Toast.makeText(
                        context,
                        "We couldn’t complete your request. Please retry.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            ContinueWithAccount()

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 14.sp, fontWeight = FontWeight(500), color = Color.Gray
                            )
                        ) {
                            append("Create An Account ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                textDecoration = TextDecoration.Underline,
                                color = PinkDark
                            )
                        ) {
                            append("Sign Up")
                        }
                    },
                    modifier = Modifier.clickable(onClick = { navController.navigate(NavRouts.SignUpScreen) })
                )
            }
        }
    }
}

