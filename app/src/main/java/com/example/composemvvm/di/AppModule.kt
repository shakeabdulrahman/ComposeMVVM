package com.example.composemvvm.di

import android.content.Context
import com.example.composemvvm.common.PreferenceDataStoreHelper
import com.example.composemvvm.data.api.ApiService
import com.example.composemvvm.data.api.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providePreferenceDataStore(@ApplicationContext context: Context): PreferenceDataStoreHelper {
        return PreferenceDataStoreHelper(context)
    }

    @Singleton
    @Provides
    fun provideTokenManager(apiService: ApiService, dataStoreHelper: PreferenceDataStoreHelper): TokenManager {
        return TokenManager(apiService, dataStoreHelper)
    }
}