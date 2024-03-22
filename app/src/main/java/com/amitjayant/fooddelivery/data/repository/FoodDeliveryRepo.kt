package com.amitjayant.fooddelivery.data.repository

import com.amitjayant.fooddelivery.data.source.api.ApiResult

interface FoodDeliveryRepo {
    suspend fun getData(): ApiResult
}