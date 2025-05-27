package com.shekharhandigol.features.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.features.BuildConfig
import com.shekharhandigol.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainProfileViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    init {
        getInformation()
    }

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private var _apiKey = MutableStateFlow("")
    val apiKey = _apiKey.asStateFlow()
    private fun getInformation() {
        viewModelScope.launch {
            datastoreRepository.getUserName().collect {
                _userName.value = it
                _apiKey.value = onlyShowLastSixDigits(BuildConfig.SPOONACULAR_API_KEY)
            }
        }

    }

    fun onlyShowLastSixDigits(text: String): String {
        Log.i("API Key: ", text)
        val length = text.length
        val maskedPart = "*".repeat(length - 6)
        return maskedPart + text.takeLast(6)
    }
}