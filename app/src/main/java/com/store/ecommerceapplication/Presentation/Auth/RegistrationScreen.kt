package com.store.ecommerceapplication.Presentation.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.store.ecommerceapplication.Navigation.Routes
import com.store.ecommerceapplication.R
import com.store.ecommerceapplication.domain.util.Result

@Composable
fun SignupScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userConfPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Observe auth state
    val authState by authViewModel.authState.collectAsState()

    // Handle navigation after successful signup
    LaunchedEffect(authState) {
        when (authState) {
            is Result.Success -> {
                navHostController.navigate(Routes.LoginScreen) {
                    popUpTo(Routes.SignUpScreen) { inclusive = true }
                }
                // Reset state to avoid multiple triggers
                authViewModel.resetAuthState()
            }
            is Result.Failure -> {
                errorMessage = (authState as Result.Failure).message
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Section
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                "Create an\n\naccount",
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 15.dp, bottom = 20.dp),
                letterSpacing = 0.5.sp
            )
        }

        // Middle Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Email Input
            AuthTextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                placeholder = "Username or Email",
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "user icon",
                        modifier = Modifier.size(25.dp)
                    )
                }
            )

            // Password Input
            AuthPasswordField(
                value = userPassword,
                onValueChange = { userPassword = it },
                placeholder = "Password"
            )

            // Confirm Password Input
            AuthPasswordField(
                value = userConfPassword,
                onValueChange = { userConfPassword = it },
                placeholder = "Confirm Password"
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Error Message
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            // Create Account Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF8817C6))
                    .clickable {
                        if (userPassword != userConfPassword) {
                            errorMessage = "Passwords do not match"
                        } else {
                            authViewModel.signup(userEmail, userPassword)
                        }
                    }
            ) {
                Text(
                    text = "Create Account",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.Center),
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // OR Continue with
            Text(
                text = "-OR Continue with-",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Social Media Row
            SocialLoginRow()
        }

        // Bottom Section - Login Redirect
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Login",
                    color = Color(0xFFB437F8),
                    fontSize = 14.sp,
                    letterSpacing = 0.5.sp,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable {
                        navHostController.navigate(Routes.LoginScreen) {
                            popUpTo(Routes.SignUpScreen) { inclusive = true }
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}




@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color(0xFFB2B2B2), RoundedCornerShape(10.dp))
            .height(70.dp)
            .fillMaxWidth()
            .background(Color(0xFFF3F3F3))
            .padding(horizontal = 15.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingIcon?.invoke()
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = placeholder, fontSize = 14.sp) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun AuthPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    AuthTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.lock),
                contentDescription = "Password",
                tint = Color.Black,
                modifier = Modifier.size(25.dp)
            )
        }
    )
}

@Composable
fun SocialLoginRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialLoginIcon(R.drawable.googleicon, "Google")
        SocialLoginIcon(R.drawable.facbookicon, "Facebook")
        SocialLoginIcon(R.drawable.appleiphone, "Apple")
    }
}

@Composable
fun SocialLoginIcon(iconRes: Int, contentDesc: String) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(60.dp)
            .border(2.dp, Color(0xFFB437F8), CircleShape)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = contentDesc,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
