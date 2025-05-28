package com.shekharhandigol.features.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.features.detailScreen.RecipeDetailScreenState.Success
import com.shekharhandigol.features.util.spoonacularApiKey
import com.shekharhandigol.usecases.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainRecipeDetailScreenViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {


    private val _detailScreenState =
        MutableStateFlow<RecipeDetailScreenState>(RecipeDetailScreenState.Loading)
    val detailScreenState = _detailScreenState.asStateFlow()

    fun getRecipeDetails(id: Int) {
        viewModelScope.launch {
            getRecipeDetailsUseCase(input = Pair(spoonacularApiKey, id)).collect { result ->
                when (result) {
                    is UiLoadState.Success -> {
                        _detailScreenState.value = Success(result.data)
                    }

                    UiLoadState.Failure -> {
                        _detailScreenState.value = RecipeDetailScreenState.Failed
                    }

                    UiLoadState.Loading -> {
                        _detailScreenState.value = RecipeDetailScreenState.Loading
                    }
                }
            }
        }
    }
}

sealed class RecipeDetailScreenState {
    data object Loading : RecipeDetailScreenState()
    data object Failed : RecipeDetailScreenState()
    data class Success(val recipeDetails: RecipeDetails) : RecipeDetailScreenState()

}