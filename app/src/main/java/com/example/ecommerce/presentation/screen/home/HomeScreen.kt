package com.example.ecommerce.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerce.presentation.uiComponents.advertisement.AnimatedCardRow
import com.example.ecommerce.presentation.uiComponents.advertisement.DealOfTheDayCard
import com.example.ecommerce.presentation.uiComponents.advertisement.NewArrivalsCard
import com.example.ecommerce.presentation.uiComponents.advertisement.ProductHighlight
import com.example.ecommerce.presentation.uiComponents.advertisement.SpecialOffersCard
import com.example.ecommerce.presentation.uiComponents.advertisement.SponserdProduCard
import com.example.ecommerce.presentation.uiComponents.advertisement.TrendingProductCard
import com.example.ecommerce.presentation.uiComponents.lazyLayout.CircleLazyRow
import com.example.ecommerce.presentation.uiComponents.lazyLayout.ProductLazyRow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var showMenu by rememberSaveable { mutableStateOf(false) }


        val spacerHeight = 16.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            LazyColumn {

                item {
                    CircleLazyRow(viewModel.categoryList)
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    AnimatedCardRow()
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    DealOfTheDayCard()
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    ProductLazyRow(viewModel.productList, imageHeight = 196)
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    SpecialOffersCard()
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    ProductHighlight()
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    TrendingProductCard()
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                   ProductLazyRow(viewModel.productList.subList(5,viewModel.productList.size-1), imageHeight = 130 )
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    NewArrivalsCard()
                    Spacer(Modifier.height(spacerHeight))
                }
                item {
                    SponserdProduCard()
                }

            }
        }

}