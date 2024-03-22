package com.amitjayant.fooddelivery.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amitjayant.fooddelivery.R
import com.amitjayant.fooddelivery.data.util.Dimensions.dimenMedium
import com.amitjayant.fooddelivery.presentation.login.components.Lottie
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme

@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Lottie(rawRes = R.raw.lottie_error, modifier = Modifier.size(200.dp))
        Text(
            modifier = Modifier.padding(vertical = dimenMedium),
            text = "Error Getting Data!!.\nPlease try again later.",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorMessagePreview() {
    FoodDeliveryTheme {
        ErrorMessage()
    }
}