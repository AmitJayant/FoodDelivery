package com.amitjayant.fooddelivery.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.amitjayant.fooddelivery.data.util.Dimensions
import com.amitjayant.fooddelivery.presentation.ui.FoodDeliveryTheme

@Composable
fun SectionHeader(headerText: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(top = Dimensions.dimenMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimensions.dimenSmall)
        )
        Text(
            modifier = Modifier.weight(2f),
            text = headerText,
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimensions.dimenSmall)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SectionHeaderPreview() {
    FoodDeliveryTheme {
        SectionHeader("Explore Restaurants")
    }
}