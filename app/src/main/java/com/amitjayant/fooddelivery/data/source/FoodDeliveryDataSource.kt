package com.amitjayant.fooddelivery.data.source

import com.amitjayant.fooddelivery.data.source.api.ApiResult
import com.amitjayant.fooddelivery.data.source.api.RetrofitEngine
import com.amitjayant.fooddelivery.domain.FoodDeliveryDto
import retrofit2.Response

class FoodDeliveryDataSource {
    suspend fun getData(): ApiResult {
        val response: Response<FoodDeliveryDto> = RetrofitEngine.foodDeliveryApi.getData()

        return if (response.isSuccessful && response.body() != null) {
            val foodDeliveryDto: FoodDeliveryDto = response.body()!!
            ApiResult.Success(foodDeliveryDto)
        } else {
            ApiResult.Error(error = response.errorBody().toString())
        }
    }
}