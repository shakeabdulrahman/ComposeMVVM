package com.example.composemvvm.di

import android.content.Context
import androidx.room.Room
import com.example.composemvvm.data.db.AppDatabase
import com.example.composemvvm.data.db.SampleDao
import com.example.composemvvm.data.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    @Singleton
    fun provideSampleDao(appDatabase: AppDatabase): SampleDao = appDatabase.sampleDao()
}

