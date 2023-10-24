package com.example.composemvvm.domain

import com.example.composemvvm.data.models.roommodel.UserTable
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    suspend fun refreshUsers()
    val allUsers: Flow<List<UserTable>>
    //suspend fun getUsersFromServer(): ResultWrapper<Response<UserDataResponse>>
}