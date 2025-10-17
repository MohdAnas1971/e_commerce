package com.example.ecommerce.presentation.uiComponents.ui_cards

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun CustomAlertBox(onClick:()-> Unit, onDismiss:()-> Unit, alertType:AlertType) {
    // Error Dialog
        AlertDialog(
            onDismissRequest =onDismiss,
            title = { Text("Login Failed") },
            text = { Text("Something went wrong. Please try again.") },
            confirmButton = {
                TextButton(onClick = onClick) {
                    Text("Retry")
                }
            }
        )
}

enum class AlertType{
        SUSSES,ERROR,NORMAL
}