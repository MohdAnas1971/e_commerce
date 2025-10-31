package com.example.ecommerce.presentation.screen.profile_screen


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Checkout",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Personal Details Section
            SectionTitle(title = "Personal Details")
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LabeledTextField(
                        label = "Email Address",
                        value = "aashifa@gmail.com",
                        leadingIcon = Icons.Default.MailOutline,
                        enabled = false
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LabeledPasswordField(
                        label = "Password",
                        value = "***********",
                        leadingIcon = Icons.Default.Lock
                    )
                    TextButton(
                        onClick = { /* Handle change password */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Change Password")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Business Address Section
            SectionTitle(title = "Business Address Details")
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LabeledTextField(
                        label = "Pin-code",
                        value = "450116",
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LabeledTextField(
                        label = "Address",
                        value = "216 St Paul's Rd,",
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LabeledTextField(
                        label = "City",
                        value = "London",
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        LabeledTextField(
                            label = "State",
                            value = "N1 2LL,",
                            modifier = Modifier.weight(1f)
                        )
                        LabeledTextField(
                            label = "Country",
                            value = "United Kingdom",
                            trailingIcon = Icons.Default.ArrowDropDown,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bank Account Section
            SectionTitle(title = "Bank Account Details")
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LabeledTextField(
                        label = "Bank Account Number",
                        value = "204356XXXXXXX",
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LabeledTextField(
                        label = "Account Holder's Name",
                        value = "Jon Doe",
                        leadingIcon = Icons.Default.Person
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LabeledTextField(
                        label = "IFSC Code",
                        value = "SBIN00428",
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            Button(
                onClick = { /* Handle save */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = {},
            leadingIcon = if (leadingIcon != null) {
                { Icon(imageVector = leadingIcon, contentDescription = null) }
            } else null,
            trailingIcon = if (trailingIcon != null) {
                { Icon(imageVector = trailingIcon, contentDescription = null) }
            } else null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            readOnly = !enabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledPasswordField(
    label: String,
    value: String,
    leadingIcon: ImageVector? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = {},
            leadingIcon = if (leadingIcon != null) {
                { Icon(imageVector = leadingIcon, contentDescription = null) }
            } else null,
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            readOnly = true
        )
    }
}

// Note: The IconButton composable would need to be imported from material3
// For simplicity, I'm using a placeholder function
@Composable
fun IconButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}


