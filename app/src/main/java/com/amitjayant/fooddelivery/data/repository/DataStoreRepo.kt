package com.amitjayant.fooddelivery.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {
    fun isUserLoggedIn(): Flow<Boolean>
    suspend fun setUserLoggedIn(isLoggedIn: Boolean)
}