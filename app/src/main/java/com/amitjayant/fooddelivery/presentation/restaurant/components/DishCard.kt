package com.amitjayant.fooddelivery.presentation.restaurant.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenMedium
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenSmall
import com.amitjayant.fooddelivery.domain.Restaurant
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme
import com.amitjayant.fooddelivery.presentation.ui.orange500

typealias Quantity = Int

@Composable
fun DishCard(dish: Restaurant.Dish) {
    var quantity by remember { mutableIntStateOf(0) }

    ElevatedCard(modifier = Modifier.padding(horizontal = dimenMedium, vertical = dimenSmall)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimenMedium)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = if (dish.vegetarian) R.drawable.vd_veg else R.drawable.vd_nonveg),
                    contentDescription = null
                )
                Text(
                    text = dish.dishName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "₹${dish.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = dish.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    QuantityButton(
                        quantity = quantity,
                        onQuantityChange = {
                            quantity = it
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DishCardPreview() {
    FoodDeliveryTheme {
        DishCard(
            dish = Restaurant.Dish(
                dishName = "Creamy Mushroom Heaven",
                description = "Delicious and creamy mushrooms in white sauce.",
                vegetarian = true,
                price = 200
            )
        )
    }
}

@Composable
fun QuantityButton(
    quantity: Quantity,
    onQuantityChange: (Quantity) -> Unit = {}
) {
    Crossfade(
        targetState = quantity == 0,
        animationSpec = tween(durationMillis = 100),
        label = ""
    ) { isQuantityZero ->
        if (isQuantityZero) {
            Button(
                modifier = Modifier.widthIn(min = 100.dp),
                onClick = { onQuantityChange(1) }) {
                Text(
                    text = stringResource(R.string.btn_add),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .width(100.dp)
                    .background(
                        color = orange500,
                        shape = RoundedCornerShape(Dimensions.dimenLarge)
                    )
                    .border(
                        width = 1.dp,
                        color = orange500,
                        shape = RoundedCornerShape(Dimensions.dimenLarge)
                    )
                    .heightIn(max = 48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { onQuantityChange(quantity - 1) }) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Remove,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = "$quantity",
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
                    fontWeight = FontWeight.ExtraBold
                )
                IconButton(onClick = { onQuantityChange(quantity + 1) }) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuantityButtonPreview() {
    FoodDeliveryTheme {
        QuantityButton(quantity = 1)
    }
}