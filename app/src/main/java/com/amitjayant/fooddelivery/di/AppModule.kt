package com.amitjayant.fooddelivery.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.amitjayant.fooddelivery.data.repository.DataStoreRepo
import com.amitjayant.fooddelivery.data.repository.DataStoreRepoImpl
import com.amitjayant.fooddelivery.data.repository.FoodDeliveryRepo
import com.amitjayant.fooddelivery.data.repository.FoodDeliveryRepoImpl
import com.amitjayant.fooddelivery.data.source.FoodDeliveryDataSource
import com.amitjayant.fooddelivery.data.util.Constants.DATASTORE_PREF
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            migrations = emptyList(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(DATASTORE_PREF) }
        )
    }

    @Provides
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepo {
        return DataStoreRepoImpl.getInstance(dataStore)
    }

    @Provides
    fun provideRestaurantRepo(): FoodDeliveryRepo = FoodDeliveryRepoImpl()

    @Provides
    fun provideRestaurantDataSource() = FoodDeliveryDataSource()
}