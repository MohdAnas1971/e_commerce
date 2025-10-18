package com.example.ecommerce.presentation.uiComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Int,
    placeholder: String,
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        placeholder = { Text(text = placeholder) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
    )

}

