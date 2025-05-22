package com.shekharhandigol.eatwelllivewell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.domain.GetCurrentThemeUseCase
import com.shekharhandigol.domain.GetFirstLaunchStateUseCase
import com.shekharhandigol.domain.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getThemeUseCase: GetCurrentThemeUseCase,
    private val getFirstLaunchStateUseCase: GetFirstLaunchStateUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
) : ViewModel() {

    private val _currentTheme = MutableStateFlow(ThemeNames.LIGHT)
    val currentTheme = _currentTheme.asStateFlow()

    private val _onboardingState = MutableStateFlow(true)
    val onboardingState = _onboardingState.asStateFlow()

    private val _userName = MutableStateFlow("Eat Well Live Well")
    val userName = _userName.asStateFlow()

    init {
        getCurrentTheme()
        getFirstLaunchState()
        getUserName()
    }

    fun getCurrentTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect {
                _currentTheme.value = it
            }
        }
    }

    fun getFirstLaunchState() {
        viewModelScope.launch {
            getFirstLaunchStateUseCase().collect {
                _onboardingState.value = it
            }
        }
    }

    fun getUserName() {
        viewModelScope.launch {
            getUserNameUseCase().collect {
                _userName.value = "Hi, $it"
            }
        }
    }


}