package com.example.ecommerce.presentation.uiComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String = "Password"
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.outline_lock_24),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(
                    id = if (isPasswordVisible) R.drawable.outline_visibility_24 else R.drawable.baseline_visibility_off_24
                ),
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick ={isPasswordVisible=!isPasswordVisible})
            )
        },
        placeholder = { Text(text =placeHolder ) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
    )

}