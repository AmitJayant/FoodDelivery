package com.amitjayant.fooddelivery.domain

import androidx.annotation.Keep

@Keep
data class Category(
    val name: String = "",
    val image: String = ""
)