package com.shekharhandigol.eatwelllivewell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.domain.GetCurrentThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getThemeUseCase: GetCurrentThemeUseCase,
) : ViewModel() {

    private val _currentTheme = MutableStateFlow(ThemeNames.LIGHT)
    val currentTheme = _currentTheme.asStateFlow()

    init {
        getCurrentTheme()
    }

    fun getCurrentTheme() {
        viewModelScope.launch {
            getThemeUseCase().collect {
                _currentTheme.value = it
            }
        }
    }


}