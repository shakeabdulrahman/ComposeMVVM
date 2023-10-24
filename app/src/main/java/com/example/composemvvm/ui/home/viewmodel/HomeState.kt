package com.example.composemvvm.ui.home.viewmodel

import com.example.composemvvm.data.models.roommodel.UserTable

data class HomeState(
    val allUsersData: List<UserTable> = listOf()
)