package com.shekharhandigol.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiInterface: ApiInterface
) : ViewModel() {

    private val _state = MutableStateFlow("Ssup ..........!!")
    val state = _state.asStateFlow()

    fun getData() {

        viewModelScope.launch {
            _state.value = apiInterface.getUsers().toString()
        }
    }


}