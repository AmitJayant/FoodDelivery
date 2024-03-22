package com.amitjayant.fooddelivery.data.repository

import com.amitjayant.fooddelivery.data.source.FoodDeliveryDataSource
import com.amitjayant.fooddelivery.data.source.api.ApiResult

class FoodDeliveryRepoImpl : FoodDeliveryRepo {
    override suspend fun getData(): ApiResult {
        return try {
            FoodDeliveryDataSource().getData()
        } catch (e: Exception) {
            ApiResult.Error("${e.message}")
        }
    }
}