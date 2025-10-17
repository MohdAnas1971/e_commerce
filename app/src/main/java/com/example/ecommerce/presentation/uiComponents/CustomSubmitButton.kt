package com.example.ecommerce.presentation.uiComponents

import android.transition.CircularPropagation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.presentation.theme.PinkDark
import com.example.ecommerce.presentation.theme.White


@Composable
 fun CustomSubmitButton (buttonText: String, isLoading: Boolean=false, onClick: () -> Unit) {

    Button(onClick = onClick,
        colors = ButtonColors(
            containerColor = PinkDark,
            contentColor = White,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        ),
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isLoading){
            CircularProgressIndicator()
        }else{
            Text(text = buttonText,
                style = MaterialTheme.typography.titleLarge, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
            )
        }
    }
}


