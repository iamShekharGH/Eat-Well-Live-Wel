package com.shekharhandigol.features.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.domain.GetRecipeDetailsUseCase
import com.shekharhandigol.features.util.spoonacularApiKey
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
            getRecipeDetailsUseCase(spoonacularApiKey to id).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _detailScreenState.value = RecipeDetailScreenState.Success(result.data)
                    }

                    is NetworkResult.Failure -> {
                        _detailScreenState.value = RecipeDetailScreenState.Failed
                    }

                    NetworkResult.Loading -> {
                        _detailScreenState.value = RecipeDetailScreenState.Loading
                    }

                    NetworkResult.NetworkError -> {
                        _detailScreenState.value = RecipeDetailScreenState.Failed
                    }
                }
            }
        }
    }
}

sealed class RecipeDetailScreenState {
    data object Loading : RecipeDetailScreenState()
    data object Failed : RecipeDetailScreenState()
    data class Success(val recipeDetailsResponse: RecipeDetailsResponse) : RecipeDetailScreenState()

}