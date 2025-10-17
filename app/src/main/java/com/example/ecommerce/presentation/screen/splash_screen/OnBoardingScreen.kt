package com.example.ecommerce.presentation.screen.splash_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.NavRouts
import com.example.masshcet.presentation.userPreferencesDataStore.UserPreferencesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    userPreferencesViewModel: UserPreferencesViewModel
) {

    var index by remember { mutableIntStateOf(0) }

    val listData = listOf(
        SplashScreenData(
            1,
            "Choose Products",
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",
            R.drawable.splash_choose_product,
            R.drawable.splash_tracker1
        ),
        SplashScreenData(
            1,
            "Make Payment",
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",
            R.drawable.splash_make_pyment,
            R.drawable.splash_tracker2
        ),
        SplashScreenData(
            1,
            "Get Your Order",
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",
            R.drawable.splash_get_started,
            R.drawable.splash_tracker3
        )
    )

    val currentPage = listData[index]

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle()) {
                                append("${index+1}")
                            }
                            withStyle(style = SpanStyle(color = Color.Gray)) {
                                append("/3")
                            }
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    Text(
                        "Skip",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.clickable(onClick = {})
                    )
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },


        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = if (index != 0) "Prev" else "   ",

                        style = MaterialTheme.typography.titleLarge,
                        color = Color.LightGray,
                        modifier = Modifier
                            .clickable(onClick = {
                                if (index > 0) {
                                    index--
                                }
                            })
                    )
                    Image(
                        painter = painterResource(currentPage.pageTracker),
                        contentDescription = null
                    )
                    Text(
                        if (index == 2) "Get Started" else "Next",
                        style = MaterialTheme.typography.titleLarge, color = Color.Red,
                        modifier = Modifier.clickable(
                            onClick = {
                                userPreferencesViewModel.setFirstTimeLogin(false)
                                if (index == 2) {
                                    navController.navigate(NavRouts.GetStartedScreen)
                                } else {
                                    index++
                                }
                            }
                        )
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {

            Image(painter = painterResource(currentPage.imagRes), contentDescription = null)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                currentPage.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                currentPage.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )

        }
    }
}




