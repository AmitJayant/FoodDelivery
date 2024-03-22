@file:OptIn(ExperimentalMaterial3Api::class)

package com.amitjayant.fooddelivery.presentation.restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.amitjayant.fooddelivery.domain.Restaurant
import com.amitjayant.fooddelivery.presentation.restaurant.components.DishCard
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme
import com.amitjayant.fooddelivery.presentation.restaurant.components.RestaurantHeader

@Composable
fun RestaurantScreen(
    restaurant: Restaurant,
    navigateUp: () -> Unit = {}
) {
    val scrollState = rememberLazyListState()
    val isAppBarTranslucent by remember {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset > 100
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navigateUp() }) {
                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .background(color = Color.White, shape = CircleShape)
                                .padding(4.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = if (isAppBarTranslucent) 0.8f else 0f)
                )
            )
        }
    ) { scaffoldPadding ->
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .padding(
                    start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = scaffoldPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = scaffoldPadding.calculateBottomPadding()
                )
        ) {
            item {
                RestaurantHeader(restaurant)
            }
            items(restaurant.menu) {
                DishCard(dish = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantScreenPreview() {
    FoodDeliveryTheme {
        RestaurantScreen(
            restaurant = Restaurant(name = "Domino's Pizza")
        )
    }
}