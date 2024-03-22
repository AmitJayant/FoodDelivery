@file:OptIn(ExperimentalMaterial3Api::class)

package com.amitjayant.fooddelivery.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.source.api.ApiResult
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenMedium
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenSmall
import com.amitjayant.fooddelivery.domain.Category
import com.amitjayant.fooddelivery.domain.Restaurant
import com.amitjayant.fooddelivery.presentation.CoreViewModel
import com.amitjayant.fooddelivery.presentation.Destinations
import com.amitjayant.fooddelivery.presentation.home.components.CategoriesList
import com.amitjayant.fooddelivery.presentation.home.components.ErrorMessage
import com.amitjayant.fooddelivery.presentation.login.components.Lottie
import com.amitjayant.fooddelivery.presentation.home.components.HomeTopAppBar
import com.amitjayant.fooddelivery.presentation.home.components.PromoCard
import com.amitjayant.fooddelivery.presentation.home.components.RestaurantCard
import com.amitjayant.fooddelivery.presentation.home.components.SectionHeader

typealias route = String
typealias argument = Restaurant

@Composable
fun HomeScreen(
    viewModel: CoreViewModel,
    onSignOut: () -> Unit = {},
    navigateTo: (route, argument) -> Unit = { _, _ -> }
) {
    val apiResult by viewModel.data.collectAsStateWithLifecycle(initialValue = ApiResult.Loading)
    val categories: List<Category> by remember(apiResult) {
        derivedStateOf { apiResult.data?.categories ?: emptyList() }
    }
    val restaurants: List<Restaurant> by remember(apiResult) {
        derivedStateOf {
            apiResult.data?.restaurants ?: emptyList()
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                scrollBehavior = scrollBehavior,
                onSignOut = onSignOut
            )
        }
    ) { scaffoldPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (apiResult.error.isNullOrBlank()) {
                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.Center),
                    visible = restaurants.isEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Lottie(
                        rawRes = R.raw.lottie_food,
                        modifier = Modifier.size(200.dp)
                    )
                }
                AnimatedVisibility(
                    visible = restaurants.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(scaffoldPadding)
                            .fillMaxSize()
                            .padding(horizontal = dimenMedium)
                    ) {
                        item {
                            PromoCard(
                                modifier = Modifier
                                    .widthIn(max = 400.dp)
                                    .heightIn(max = 160.dp)
                                    .padding(top = dimenMedium)
                            )
                        }
                        item {
                            SectionHeader(
                                headerText = stringResource(id = R.string.category_header)
                            )
                        }
                        item {
                            CategoriesList(categories)
                        }
                        item {
                            SectionHeader(
                                modifier = Modifier.padding(bottom = dimenSmall),
                                headerText = stringResource(id = R.string.restaurants_header)
                            )
                        }
                        items(restaurants) {
                            RestaurantCard(restaurant = it) {
                                navigateTo(Destinations.RestaurantScreen.route, it)
                            }
                        }
                    }
                }
            } else {
                // Show error composable.
                ErrorMessage(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}