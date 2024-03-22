package com.amitjayant.fooddelivery.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions
import com.amitjayant.fooddelivery.domain.Restaurant
import com.amitjayant.fooddelivery.presentation.ui.amber500
import com.amitjayant.fooddelivery.presentation.ui.orange500
import com.amitjayant.fooddelivery.presentation.ui.red500

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = Dimensions.dimenSmall)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
    ) {
        Card(
            modifier = Modifier
                .width(120.dp)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context)
                    .data(restaurant.image)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = Dimensions.dimenLarge),
                        strokeCap = StrokeCap.Round
                    )
                },
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimensions.dimenMedium, vertical = Dimensions.dimenSmall),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = restaurant.category.joinToString(", "),
                style = MaterialTheme.typography.titleSmall
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                modifier = Modifier.padding(top = Dimensions.dimenMedium),
                text = stringResource(R.string.free_delivery),
                style = MaterialTheme.typography.titleMedium
                    .copy(brush = Brush.linearGradient(colors = listOf(orange500, red500))),
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantCardPreview() {
    RestaurantCard(Restaurant())
}