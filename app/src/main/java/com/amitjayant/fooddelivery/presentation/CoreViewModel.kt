package com.amitjayant.fooddelivery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amitjayant.fooddelivery.data.repository.DataStoreRepo
import com.amitjayant.fooddelivery.data.repository.FoodDeliveryRepo
import com.amitjayant.fooddelivery.data.source.api.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    private val dataStoreRepo: DataStoreRepo,
    private val foodDeliveryRepo: FoodDeliveryRepo
) : ViewModel() {
    private val _data: MutableStateFlow<ApiResult> = MutableStateFlow(ApiResult.Loading)
    val data: StateFlow<ApiResult> = _data.asStateFlow()

    fun isUserLoggedIn(): Flow<Boolean> = dataStoreRepo.isUserLoggedIn()

    fun signIn() {
        viewModelScope.launch {
            dataStoreRepo.setUserLoggedIn(true)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            dataStoreRepo.setUserLoggedIn(false)
        }
    }

    fun getData() {
        viewModelScope.launch {
            _data.value = foodDeliveryRepo.getData()
        }
    }
}