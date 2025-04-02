package com.shekharhandigol.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.SpoonaclularApiInterface
import com.shekharhandigol.core.models.searchRecepies.SearchRecipeResponse
import com.shekharhandigol.features.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


const val key = BuildConfig.SPOONACULAR_API_KEY

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiInterface: SpoonaclularApiInterface
) : ViewModel() {


    private val _state = MutableStateFlow<HomeScreenUiStates>(HomeScreenUiStates.SuccessQuery(SearchRecipeResponse()))
    val state = _state.asStateFlow()

    private val _dashboardData = MutableStateFlow(DashboardData())
    val dashboardData = _dashboardData.asStateFlow()


    fun getData() {

        viewModelScope.launch {
//            _state.value = apiInterface.getUsers().toString()
        }
    }

    fun getRecipes(newValue: String) {
        viewModelScope.launch {
            _state.value = HomeScreenUiStates.SuccessQuery(apiInterface.getRecipes(key, newValue))
        }
    }
}

sealed class HomeScreenUiStates {
    data object LandingScreen : HomeScreenUiStates()
    data object LoadingScreen : HomeScreenUiStates()
    data object FailedRequest : HomeScreenUiStates()
    data class SuccessQuery(val data: SearchRecipeResponse) : HomeScreenUiStates()
}

data class DashboardData(
    val featuredRecipes: List<Recipe> = listOf(
        Recipe("Bread", "Bread Desc", "Bread Image"),
        Recipe("Pasta", "Pasta Desc", "Pasta Image"),
        Recipe("Pizza", "Pizza Desc", "Pizza Image"),
        Recipe("Burger", "Burger Desc", "Burger Image"),
    ),
    val categories: List<String> = listOf(
        "Lunch", "Dinner", "Vegetarian", "Vegan", "Breakfast"
    ),
    val popularRecipes: List<Recipe> = listOf(
        Recipe("Omlet", "Omlet Desc", "Omlet Image"),
        Recipe("Dal Makhani", "Dal Makhani Desc", "Dal Makhani Image"),
        Recipe("Chicken Curry", "Chicken Curry Desc", "Chicken Curry Image")
    )
)
/*
    Things to do :~
    1. Create card for recipe.
    2. Create Dashboard with sections.
    3. Put a Search icon on Toolbar.
 */