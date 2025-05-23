package com.shekharhandigol.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.ThemeNames
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
                _currentTheme.value = theme
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
            getFirstLaunchStateUseCase().collect { state ->
                _onboardingState.value = state
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
            getUserNameUseCase().collect { name ->
                _userName.value = name
            }
        }
    }

}