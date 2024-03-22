package com.amitjayant.fooddelivery.domain

import androidx.annotation.Keep

@Keep
data class FoodDeliveryDto(
    val categories: List<Category> = emptyList(),
    val restaurants: List<Restaurant> = emptyList()
)