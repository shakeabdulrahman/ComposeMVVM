package com.example.composemvvm.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composemvvm.common.Route
import com.example.composemvvm.ui.main.viewmodel.MainChannel
import com.example.composemvvm.ui.main.viewmodel.MainEvents
import com.example.composemvvm.ui.main.viewmodel.MainState
import com.example.composemvvm.ui.main.viewmodel.MainViewModel
import com.example.composemvvm.ui.navigation.AppBottomNavigation
import com.example.composemvvm.ui.navigation.AppNavigation
import com.example.composemvvm.ui.navigation.NavigationDrawerContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var state: MainState
    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainViewModel = hiltViewModel()
            state = mainViewModel.state.value
            navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            BackHandler {
                if (drawerState.isOpen) {
                    lifecycleScope.launch {
                        drawerState.close()
                    }
                } else {
                    onBackPressedDispatcher.onBackPressed()
                }
            }

            LaunchedEffect(key1 = Unit) {
                mainViewModel.mainChannel.collect { event ->
                    when (event) {
                        is MainChannel.ShowSuccessToast -> {
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is MainChannel.ShowErrorToast -> {
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }

            val scope = rememberCoroutineScope()
            val openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }

            ModalNavigationDrawer(drawerState = drawerState,
                gesturesEnabled = true,
                drawerContent = {
                    ModalDrawerSheet(
                        drawerShape = RoundedCornerShape(
                            topEnd = 27.dp,
                            bottomEnd = 27.dp
                        ), drawerContainerColor = Color.White
                    ) {
                        NavigationDrawerContent {
                            when (it.route) {
                                Route.DRAWER_DUMMY1_SCREEN, Route.DRAWER_DUMMY2_SCREEN -> {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(it.route)
                                }
                            }
                        }
                    }

                }) {
                Scaffold(containerColor = Color.White,
                    contentColor = Color.White,
                    content = {
                        AppNavigation(
                            navController = navController,
                            activity = this,
                            mainViewModel = mainViewModel,
                            openDrawer = openDrawer,
                            drawerState = drawerState
                        )
                    },
                    bottomBar = {
                        if (state.showBottomBar)
                            AppBottomNavigation(selectedValue = { item ->
                                mainViewModel.onEvent(MainEvents.SelectedBottomBar(item))
                            }, navigate = { item ->
                                navController.navigate(item.route) {
                                    popUpTo(item.route)
                                    navController.popBackStack()
                                }
                            }, navKey = state.bottomBar)
                    })
            }
        }
    }
}


