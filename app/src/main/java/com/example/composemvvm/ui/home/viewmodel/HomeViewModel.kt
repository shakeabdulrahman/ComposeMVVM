package com.example.composemvvm.ui.home.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvvm.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    val allUsers = usersRepository.allUsers

    fun onEvent(events: HomeEvent) {
        when (events) {
            is HomeEvent.GetAllUsers -> {
                viewModelScope.launch {
                    usersRepository.refreshUsers()
                    getAllUsers()
                }
            }

            is HomeEvent.ShowToastMessage -> {
              /*  if (events.type == application.getString(R.string.ocean))
                    Toast.makeText(application, "${_state.value.allOceanData}", Toast.LENGTH_SHORT)
                        .show()
                else
                    Toast.makeText(application, "${_state.value.allWoodsData}", Toast.LENGTH_SHORT)
                        .show()*/
            }
        }
    }

    private suspend fun getAllUsers() {
        usersRepository.allUsers.collect {
            _state.value = _state.value.copy(allUsersData = it)
        }
    }
}