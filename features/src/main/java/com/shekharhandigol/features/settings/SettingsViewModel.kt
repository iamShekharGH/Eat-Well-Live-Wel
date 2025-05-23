package com.shekharhandigol.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.domain.GetCurrentThemeUseCase
import com.shekharhandigol.domain.GetFirstLaunchStateUseCase
import com.shekharhandigol.domain.GetUserNameUseCase
import com.shekharhandigol.domain.SaveUserNameUseCase
import com.shekharhandigol.domain.SetCurrentThemeUseCase
import com.shekharhandigol.domain.SetFirstLaunchStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getThemeUseCase: GetCurrentThemeUseCase,
    private val setThemeUseCase: SetCurrentThemeUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val setFirstLaunchStateUseCase: SetFirstLaunchStateUseCase,
    private val getFirstLaunchStateUseCase: GetFirstLaunchStateUseCase
) : ViewModel() {

    private val _currentTheme = MutableStateFlow(ThemeNames.LIGHT)
    val currentTheme = _currentTheme.asStateFlow()
    private val _onboardingState = MutableStateFlow(true)
    val onboardingState = _onboardingState.asStateFlow()
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    fun loadInitialSettings() {
        getCurrentThemeFromDatastore()
        getOnboardingStateFromDatastore()
        getUserNameFromDatastore()
    }

    fun onThemeChange(theme: ThemeNames) {
        viewModelScope.launch {
            setThemeUseCase(theme)
        }
    }

    private fun getCurrentThemeFromDatastore() {
        viewModelScope.launch {
            getThemeUseCase().collect { theme ->
                when (theme) {
                    is UiLoadState.Success -> {
                        _currentTheme.value = theme.data
                    }

                    is UiLoadState.Failure -> {
                        _currentTheme.value = ThemeNames.LIGHT
                    }

                    UiLoadState.Loading -> {
                        _loadingState.value = true
                    }
                }
            }
        }
    }

    fun onboardingStateChanged(state: Boolean) {
        viewModelScope.launch {
            setFirstLaunchStateUseCase(state)
        }
    }

    private fun getOnboardingStateFromDatastore() {
        viewModelScope.launch {
            getFirstLaunchStateUseCase().collect { result ->
                when (result) {
                    is UiLoadState.Success -> {
                        _onboardingState.value = result.data
                    }

                    is UiLoadState.Failure -> {
                        _onboardingState.value = false
                    }

                    UiLoadState.Loading -> {
                        _loadingState.value = false
                    }
                }
            }
        }
    }

    fun onUsernameChange(name: String) {
        viewModelScope.launch {
            saveUserNameUseCase(name)
        }
    }

    private fun getUserNameFromDatastore() {
        viewModelScope.launch {
            getUserNameUseCase().collect { result ->
                when (result) {
                    is UiLoadState.Success -> {
                        _userName.value = result.data
                    }

                    is UiLoadState.Failure -> {
                        _userName.value = "" // Default to empty on failure
                    }

                    UiLoadState.Loading -> {
                        _loadingState.value = true
                    }
                }
            }
        }
    }

}
