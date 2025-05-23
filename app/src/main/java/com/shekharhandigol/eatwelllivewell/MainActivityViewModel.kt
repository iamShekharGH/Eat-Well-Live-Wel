package com.shekharhandigol.eatwelllivewell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.domain.GetCurrentThemeUseCase
import com.shekharhandigol.domain.GetFirstLaunchStateUseCase
import com.shekharhandigol.domain.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getThemeUseCase: GetCurrentThemeUseCase,
    private val getFirstLaunchStateUseCase: GetFirstLaunchStateUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
) : ViewModel() {

    private val _currentThemeState = MutableStateFlow<UiLoadState<ThemeNames>>(UiLoadState.Loading)
    private val _onboardingState = MutableStateFlow<UiLoadState<Boolean>>(UiLoadState.Loading)
    private val _userNameState = MutableStateFlow<UiLoadState<String>>(UiLoadState.Loading)
    private val _isDataReady = MutableStateFlow(false)

    val currentThemeState = _currentThemeState.asStateFlow()
    val onboardingState = _onboardingState.asStateFlow()
    val userNameState = _userNameState.asStateFlow()
    val isDataReady = _isDataReady.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {

        getCurrentTheme()
        getFirstLaunchState()
        getUserName()
        waitForResults()
    }

    private fun waitForResults() {
        viewModelScope.launch {
            combine(
                currentThemeState,
                onboardingState,
                userNameState
            ) { states ->
                states.all { it is UiLoadState.Success }
            }.collect {
                _isDataReady.value = it
            }
        }
    }

    private fun getCurrentTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect { themes ->
                when (themes) {
                    is UiLoadState.Success -> {
                        _currentThemeState.value = UiLoadState.Success(data = themes.data)
                    }

                    is UiLoadState.Failure -> {
                        _currentThemeState.value = UiLoadState.Failure
                    }

                    UiLoadState.Loading -> {
                        _currentThemeState.value = UiLoadState.Loading
                    }
                }
            }
        }
    }

    private fun getFirstLaunchState() {
        viewModelScope.launch {
            getFirstLaunchStateUseCase().collect { onboardingState ->
                when (onboardingState) {
                    is UiLoadState.Success -> {
                        _onboardingState.value = UiLoadState.Success(data = onboardingState.data)
                    }

                    is UiLoadState.Failure -> {
                        _onboardingState.value =
                            UiLoadState.Failure // Or a default success like UiLoadState.Success(true)
                    }

                    UiLoadState.Loading -> {
                        _onboardingState.value = UiLoadState.Loading
                    }
                }
            }
        }
    }

    private fun getUserName() {
        viewModelScope.launch {
            getUserNameUseCase().collect { userNameState ->
                when (userNameState) {
                    is UiLoadState.Success -> {
                        _userNameState.value = UiLoadState.Success("Hi, ${userNameState.data}")
                    }

                    is UiLoadState.Failure -> {
                        _userNameState.value =
                            UiLoadState.Success("Eat Well Live Well") // Default or error value
                    }

                    UiLoadState.Loading -> {
                        _userNameState.value = UiLoadState.Loading
                    }
                }
            }
        }
    }
}