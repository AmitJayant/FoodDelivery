package com.amitjayant.fooddelivery.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme

@Composable
fun PromoCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { },
        colors = CardDefaults.cardColors()
    ) {
        Box(contentAlignment = Alignment.CenterStart) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                painter = painterResource(id = R.drawable.img_pizza),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(Dimensions.dimenMedium)
            ) {
                Text(
                    text = stringResource(R.string.promo_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = stringResource(R.string.promo_description),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(top = Dimensions.dimenSmall),
                    text = stringResource(id = R.string.promo_code),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = Color.White, contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = stringResource(id = R.string.order_now))
                }
            }
        }
    }
}

@Preview
@Composable
private fun PromoCardPreview() {
    FoodDeliveryTheme {
        PromoCard()
    }
}