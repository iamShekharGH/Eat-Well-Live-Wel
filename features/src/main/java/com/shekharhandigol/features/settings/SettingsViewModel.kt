package com.shekharhandigol.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.domain.GetCurrentThemeUseCase
import com.shekharhandigol.domain.SetCurrentThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getThemeUseCase: GetCurrentThemeUseCase,
    private val setThemeUseCase: SetCurrentThemeUseCase
) : ViewModel() {

    private val _currentTheme = MutableStateFlow(ThemeNames.LIGHT)
    val currentTheme = _currentTheme.asStateFlow()

    fun onThemeChange(theme: ThemeNames) {
        viewModelScope.launch {
            setThemeUseCase.invoke(theme)
        }
    }

    fun getCurrentTheme() {
        viewModelScope.launch {
            getThemeUseCase.invoke(Unit).collect { theme ->
                _currentTheme.value = theme
            }
        }
    }

}