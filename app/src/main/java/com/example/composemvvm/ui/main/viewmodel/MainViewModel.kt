package com.example.composemvvm.ui.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private val _mainChannel = Channel<MainChannel>()
    val mainChannel = _mainChannel.receiveAsFlow()

    fun onEvent(events: MainEvents) {
        when (events) {
            is MainEvents.SelectedBottomBar -> {
                _state.value = _state.value.copy(bottomBar = events.bottomBar)
            }

            is MainEvents.ShowBottomBar -> {
                _state.value = _state.value.copy(showBottomBar = events.showBottomBar)
            }
        }
    }

    /* private suspend fun logout() {
        _state.value = _state.value.copy(isLoading = true)

        when (val logoutResponse = authenticationRepository.logout()) {
            is ResultWrapper.GenericError -> {
                _state.value = _state.value.copy(isLoading = false)
                _mainChannel.send(
                    MainChannel.ShowErrorToast(
                        logoutResponse.message!!
                    )
                )

            }

            is ResultWrapper.NetworkError -> {
                _state.value = _state.value.copy(isLoading = false)
                _mainChannel.send(
                    MainChannel.ShowErrorToast(
                        logoutResponse.message
                    )
                )
            }

            is ResultWrapper.Success -> {
                _state.value = _state.value.copy(isLoading = false)

                logoutResponse.value.let { response ->

                    if (response.statusCode == 200) {
                        _mainChannel.send(MainChannel.ShowSuccessToast(response.message!!))
                    }
                }
            }
        }
    } */
}

sealed class MainChannel {
    data class ShowErrorToast(val message: String) : MainChannel()
    data class ShowSuccessToast(val message: String) : MainChannel()
}

