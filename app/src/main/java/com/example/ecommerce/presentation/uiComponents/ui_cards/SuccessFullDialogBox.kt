package com.example.ecommerce.presentation.uiComponents.ui_cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ecommerce.R
import com.example.ecommerce.presentation.theme.White
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessFullDialogBox() {
        BasicAlertDialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true,
                usePlatformDefaultWidth = true
            ),
            modifier = Modifier
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(18.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.success_dialog_background),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        )

                        Text("Payment Done Successfully.", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
}




@Composable
fun SuccessDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White),
            shadowElevation = 16.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Circular pink component with check icon
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(60.dp))
                        .background(Color(0xFFFFC0CB))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Draw random gray stars in the background
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val random = Random(123) // Seed for consistent placement

                        // Draw 8-12 stars at random positions
                        repeat(random.nextInt(8, 13)) {
                            val x = random.nextFloat() * size.width
                            val y = random.nextFloat() * size.height
                            val size = random.nextFloat() * 8.dp.toPx() + 4.dp.toPx()

                            // Draw a simple 5-point star
                            drawStar(Offset(x, y), size, Color.LightGray.copy(alpha = 0.7f))
                        }
                    }

                    // Check icon
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        modifier = Modifier.size(50.dp),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Success message
                Text(
                    text = "Payment done successfully",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                // OK button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC0CB),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "OK",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        }
    }
}

// Function to draw a simple 5-point star
private fun drawStar(center: Offset, size: Float, color: Color) {
    val outerRadius = size
    val innerRadius = size / 2
    val points = 5

    for (i in 0 until points * 2) {
        val angle = Math.PI / points * i - Math.PI / 2
        val radius = if (i % 2 == 0) outerRadius else innerRadius
        val x = center.x + radius * kotlin.math.cos(angle).toFloat()
        val y = center.y + radius * kotlin.math.sin(angle).toFloat()

        if (i > 0) {
            // Draw line from previous point to current point
            val prevAngle = Math.PI / points * (i - 1) - Math.PI / 2
            val prevRadius = if ((i - 1) % 2 == 0) outerRadius else innerRadius
            val prevX = center.x + prevRadius * kotlin.math.cos(prevAngle).toFloat()
            val prevY = center.y + prevRadius * kotlin.math.sin(prevAngle).toFloat()

            // This would normally draw lines, but for simplicity we're just drawing points
            // In a real implementation, you'd use drawLine or drawPath for a proper star
        }
    }

    // For simplicity, we'll just draw a circle as a placeholder for stars
    // In a production app, you'd implement proper star drawing
   /* androidx.compose.ui.graphics.drawscope.drawCircle(
        color = color,
        radius = size / 2,
        center = center
    )*/
}

@Composable
fun SuccessDialogPreview() {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        SuccessDialog(onDismiss = { showDialog = false })
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC0CB),
                    contentColor = Color.White
                )
            ) {
                Text("Show Success Dialog")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessDialogPreviewComposable() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SuccessDialogPreview()
        }
    }
}

