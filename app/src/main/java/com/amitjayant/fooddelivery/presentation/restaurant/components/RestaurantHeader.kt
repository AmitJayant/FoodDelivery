package com.amitjayant.fooddelivery.presentation.restaurant.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions
import com.amitjayant.fooddelivery.domain.Restaurant
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme
import com.amitjayant.fooddelivery.presentation.ui.amber500
import com.amitjayant.fooddelivery.presentation.ui.orange500
import com.amitjayant.fooddelivery.presentation.ui.red500

@Composable
fun RestaurantHeader(restaurant: Restaurant) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = ImageRequest.Builder(context)
                .data(restaurant.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 260.dp),
            shape = MaterialTheme.shapes.medium.copy(
                topStart = CornerSize(Dimensions.dimenLarge),
                topEnd = CornerSize(Dimensions.dimenLarge),
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            ),
            colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.dimenMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(vertical = Dimensions.dimenSmall),
                    text = restaurant.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = restaurant.category.joinToString(", "),
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    modifier = Modifier.padding(vertical = Dimensions.dimenSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = amber500
                    )
                    Text(
                        text = "${restaurant.rating} (${restaurant.reviews} ${stringResource(id = R.string.reviews)})",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    modifier = Modifier.padding(top = Dimensions.dimenSmall),
                    text = stringResource(R.string.free_delivery),
                    style = MaterialTheme.typography.titleMedium
                        .copy(brush = Brush.linearGradient(colors = listOf(orange500, red500))),
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Preview
@Composable
private fun RestaurantHeaderPreview() {
    FoodDeliveryTheme {
        RestaurantHeader(
            restaurant = Restaurant(
                name = "Domino's Pizza",
                category = listOf("Pizza", "Pasta"),
                image = "https://content.jdmagicbox.com/comp/delhi/w5/011pxx11.xx11.200213190213.y7w5/catalogue/dominos-pizza-indralok-delhi-pizza-outlets-0haabsmmls.jpg"
            )
        )
    }
}