package com.example.composemvvm.ui.main.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.composemvvm.common.BottomNavigationScreens
import com.example.composemvvm.data.models.roommodel.UserTable

data class MainState(
    val bottomBar: BottomNavigationScreens = BottomNavigationScreens.HOME,
    val bottomTabItemLists: SnapshotStateList<BottomNavigationScreens> = mutableStateListOf(),
    val showBottomBar: Boolean = false,
    val isLoading: Boolean = false,
    val allUsersData: List<UserTable> = emptyList()
)