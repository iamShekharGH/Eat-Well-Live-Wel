package com.shekharhandigol.features.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.repository.RoomDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFavouriteScreenViewModel @Inject constructor(
    private val roomDbRepository: RoomDbRepository
) : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes = _recipes.asStateFlow()

    fun getRecipesFromDb() {
        viewModelScope.launch {
            _recipes.value = roomDbRepository.getFavouriteRecipes()
        }
    }

}