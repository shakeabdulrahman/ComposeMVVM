package com.example.composemvvm.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composemvvm.R
import com.example.composemvvm.common.AppSetStatusBarColor
import com.example.composemvvm.common.BottomNavigationScreens
import com.example.composemvvm.common.NavigationDrawerScreens
import com.example.composemvvm.common.Route
import com.example.composemvvm.ui.features.noRippleClickable
import com.example.composemvvm.ui.home.HomeScreen
import com.example.composemvvm.ui.home.viewmodel.HomeViewModel
import com.example.composemvvm.ui.main.MainActivity
import com.example.composemvvm.ui.main.viewmodel.MainEvents
import com.example.composemvvm.ui.main.viewmodel.MainViewModel
import com.example.composemvvm.ui.theme.AppQATextColor
import com.example.composemvvm.ui.theme.AppReflectionTagColor
import com.example.composemvvm.ui.theme.AppSelectedTextColor
import com.example.composemvvm.ui.theme.AppUnSelectedTextColor
import com.example.composemvvm.ui.theme.circularStdBold
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun AppNavigation(
    navController: NavHostController,
    activity: MainActivity,
    mainViewModel: MainViewModel,
    openDrawer: () -> Job,
    drawerState: DrawerState
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Route.HOME_SCREEN,
        ) {
            composable(
                route = Route.HOME_SCREEN,
            ) {
                // Setting the bottom tab bar visibility
                mainViewModel.onEvent(MainEvents.ShowBottomBar(showBottomBar = true))

                AppSetStatusBarColor.appSetStatusBarColor(
                    activity = activity, color = R.color.white
                )

                BackHandler {
                    if (drawerState.isOpen) {
                        scope.launch {
                            drawerState.close()
                        }
                    } else {
                        activity.finish()
                    }
                }

                HomeScreen(homeViewModel = homeViewModel,
                    openDrawer = {
                        openDrawer.invoke()
                    })
            }

            composable(
                route = Route.PROFILE_SCREEN,
            ) {
                mainViewModel.onEvent(MainEvents.ShowBottomBar(showBottomBar = true))

                AppSetStatusBarColor.appSetStatusBarColor(
                    activity = activity, color = R.color.white
                )

                // ProfileScreen()
            }

            composable(
                route = Route.DRAWER_DUMMY1_SCREEN,
            ) {
                HomeScreen(homeViewModel = homeViewModel,
                    openDrawer = {
                        openDrawer.invoke()
                    })
            }

            composable(
                route = Route.DRAWER_DUMMY2_SCREEN,
            ) {
                HomeScreen(homeViewModel = homeViewModel,
                    openDrawer = {
                        openDrawer.invoke()
                    })
            }
        }
    }
}


@Composable
fun AppBottomNavigation(
    height: Dp = 70.dp,
    selectedValue: (BottomNavigationScreens) -> Unit,
    navigate: (BottomNavigationScreens) -> Unit,
    navKey: BottomNavigationScreens) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val bottomTabItems = listOf(
            BottomNavigationScreens.HOME,
            BottomNavigationScreens.PROFILE
        )

        bottomTabItems.forEach { item ->
            AppBottomNavigationItem(isSelected = navKey.route == item.route,
                item = item,
                modifier = Modifier
                    .weight(1f)
                    .height(height)
                    .clickable {
                        if (navKey.route != item.route) {
                            selectedValue(item)
                            navigate(item)
                        }
                    })
        }
    }
}

@Composable
fun AppBottomNavigationItem(
    isSelected: Boolean,
    item: BottomNavigationScreens,
    modifier: Modifier,
) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally),

            ) {
            Column {
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = if (isSelected) painterResource(id = item.icon) else painterResource(
                        id = item.unSelectedIcon
                    ),
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 7.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(item.title),
                    fontSize = 12.sp,
                    color = if (isSelected) AppSelectedTextColor else AppUnSelectedTextColor,
                    fontFamily = MaterialTheme.typography.titleSmall.fontFamily
                )
            }
        }
    }
}

@Composable
fun NavigationDrawerContent(
    navigate: (NavigationDrawerScreens) -> Unit,
) {
    val nameChar = remember { mutableStateOf("Name") }

    val navigationDrawerList = listOf(
        NavigationDrawerScreens.Dummy1,
        NavigationDrawerScreens.Dummy2
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 70.dp))

        Row(
            modifier = Modifier.padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.drawer_name_shape_background),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                )

                Text(
                    text = if (nameChar.value.isEmpty()) nameChar.value else nameChar.value[0].toString()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    fontFamily = circularStdBold,
                    fontSize = 24.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(16.dp),
                    color = AppReflectionTagColor,
                    fontWeight = FontWeight.W700
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = nameChar.value.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                fontFamily = circularStdBold,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = AppQATextColor,
                fontWeight = FontWeight.W700
            )

        }

        Spacer(modifier = Modifier.size(24.dp))

        Spacer(modifier = Modifier.size(24.dp))

        LazyColumn {
            itemsIndexed(navigationDrawerList) { _: Int, item: NavigationDrawerScreens ->
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .noRippleClickable {
                            navigate.invoke(item)
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    Text(
                        text = stringResource(id = item.title),
                        fontFamily = circularStdBold,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        color = AppQATextColor,
                        fontWeight = FontWeight.W700
                    )
                }

                Spacer(modifier = Modifier.size(32.dp))
            }
        }
    }
}

