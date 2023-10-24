package com.example.composemvvm.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composemvvm.R
import com.example.composemvvm.ui.home.viewmodel.HomeEvent
import com.example.composemvvm.ui.home.viewmodel.HomeViewModel
import com.example.composemvvm.ui.main.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    openDrawer: () -> Unit
) {

    val homeState = homeViewModel.state.value
    val isVisible = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        homeViewModel.onEvent(HomeEvent.GetAllUsers)
    }

    LaunchedEffect(Unit) {
        isVisible.value = true
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        if (isVisible.value)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                    )

                    Row {
                        Image(painter = painterResource(id = R.drawable.app_drawer_icon),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                openDrawer.invoke()
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(homeState.allUsersData) { item ->
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.padding(16.dp)) {
                                AsyncImage(
                                    model = item.avatar,
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                Spacer(modifier = Modifier.size(32.dp))

                                Column {
                                    Text(text = item.firstName + " " + item.lastName)
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(text = item.email)
                                }
                            }
                        }
                    }
                }
            }
    }
}




