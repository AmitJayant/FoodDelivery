package com.amitjayant.fooddelivery.domain

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Keep
@Parcelize
data class Restaurant(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val category: List<String> = emptyList(),
    val rating: Float = 0.0f,
    val reviews: String = "",
    val menu: @RawValue List<Dish> = emptyList(),
) : Parcelable {
    @Keep
    @Parcelize
    data class Dish(
        @SerializedName("dish_id") val dishId: Int = 0,
        @SerializedName("dish_name") val dishName: String = "",
        val description: String = "",
        val vegetarian: Boolean = true,
        val price: Int = 0
    ) : Parcelable
}