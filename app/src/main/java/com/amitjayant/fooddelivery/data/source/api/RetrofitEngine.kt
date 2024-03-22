package com.amitjayant.fooddelivery.data.source.api

import com.amitjayant.fooddelivery.data.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitEngine {
    val foodDeliveryApi: FoodDeliveryApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodDeliveryApi::class.java)
    }
}