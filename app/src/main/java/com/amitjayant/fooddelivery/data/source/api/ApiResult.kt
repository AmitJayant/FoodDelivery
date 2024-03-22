package com.amitjayant.fooddelivery.data.source.api

import com.amitjayant.fooddelivery.domain.FoodDeliveryDto

sealed class ApiResult(
    val data: FoodDeliveryDto? = null,
    val error: String? = null
) {
    data object Loading : ApiResult()
    class Success(data: FoodDeliveryDto) : ApiResult(data)
    class Error(error: String) : ApiResult(error = error)
}