package com.example.composemvvm.ui.main.viewmodel

import com.example.composemvvm.common.BottomNavigationScreens

sealed class MainEvents {
    data class SelectedBottomBar(val bottomBar: BottomNavigationScreens) : MainEvents()
    data class ShowBottomBar(val showBottomBar: Boolean = false) : MainEvents()
}