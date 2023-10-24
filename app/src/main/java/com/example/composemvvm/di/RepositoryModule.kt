package com.example.composemvvm.di

import com.example.composemvvm.data.api.ApiService
import com.example.composemvvm.data.db.UserDao
import com.example.composemvvm.data.repository.UsersRepository
import com.example.composemvvm.domain.IUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepo(userDao: UserDao, apiService: ApiService): IUsersRepository =
        UsersRepository(userDao = userDao, apiService = apiService)
}