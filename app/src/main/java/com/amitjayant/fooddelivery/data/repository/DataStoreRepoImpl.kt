package com.amitjayant.fooddelivery.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.amitjayant.fooddelivery.data.util.Constants.DATASTORE_USER_LOGGED_IN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepoImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : DataStoreRepo {
    companion object {
        @Volatile
        private var instance: DataStoreRepo? = null

        fun getInstance(dataStore: DataStore<Preferences>): DataStoreRepo {
            return instance ?: synchronized(this) {
                return DataStoreRepoImpl(dataStore)
            }
        }
    }

    override fun isUserLoggedIn(): Flow<Boolean> = dataStore.data.map {
        it[booleanPreferencesKey(DATASTORE_USER_LOGGED_IN)] ?: false
    }

    override suspend fun setUserLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(DATASTORE_USER_LOGGED_IN)] = isLoggedIn
        }
    }
}