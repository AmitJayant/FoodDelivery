package com.amitjayant.fooddelivery.data.source.api

import com.amitjayant.fooddelivery.data.util.Constants
import com.amitjayant.fooddelivery.domain.FoodDeliveryDto
import retrofit2.Response
import retrofit2.http.GET

interface FoodDeliveryApi {
    @GET(Constants.API_ENDPOINT)
    suspend fun getData(): Response<FoodDeliveryDto>
}