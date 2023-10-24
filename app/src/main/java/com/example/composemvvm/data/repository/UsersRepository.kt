package com.example.composemvvm.data.repository

import android.util.Log
import com.example.composemvvm.data.api.ApiService
import com.example.composemvvm.data.db.UserDao
import com.example.composemvvm.data.models.roommodel.UserTable
import com.example.composemvvm.domain.IUsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(private val userDao: UserDao, val apiService: ApiService) : IUsersRepository {

    /* override suspend fun getUsersFromServer(): ResultWrapper<Response<UserDataResponse>> =
        safeApiCall(Dispatchers.IO) {
            apiService.getAllUsers()
        } */

    override val allUsers: Flow<List<UserTable>> = userDao.getAllUsers()

    override suspend fun refreshUsers() {
        try {
            val response = apiService.getAllUsers()
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    val users = response.body()?.data?.map { user ->
                        UserTable(
                            id = user.id!!,
                            email = user.email!!,
                            firstName = user.firstName!!,
                            lastName = user.lastName!!,
                            avatar = user.avatar!!
                        )
                    }
                    if (users != null) {
                        userDao.insertAll(users)
                    } // Insert the mapped data into the database
                }
            }
        } catch (e: Exception) {
            Log.e("USER_API", e.message.toString())
        }
    }
}