package com.shekharhandigol.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.features.util.spoonacularApiKey
import com.shekharhandigol.usecases.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<HomeScreenUiStates>(HomeScreenUiStates.Dashboard)
    val state = _state.asStateFlow()

    private val _dashboardData = MutableStateFlow(DashboardData())
    val dashboardData = _dashboardData.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchBarState = MutableStateFlow(false)
    val searchBarState = _searchBarState.asStateFlow()


    fun emptySearchString() {
        _searchText.value = ""
    }

    @OptIn(FlowPreview::class)
    fun searchTextChanged(text: String) {
        _searchText.value = text

        viewModelScope.launch {
            searchText.debounce(500L)
                .filter { text.isNotBlank() && text.isNotEmpty() && text.length >= 3 }
                .collectLatest { text ->
                    _state.value = HomeScreenUiStates.LoadingScreen
                    getSearchRecipeResult(text)
            }
        }
    }

    private suspend fun getSearchRecipeResult(query: String) {
        getRecipeUseCase(spoonacularApiKey to query).collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _state.value = HomeScreenUiStates.SuccessQuery(result.data)
                }

                is NetworkResult.Failure -> {
                    _state.value = HomeScreenUiStates.FailedRequest
                }

                NetworkResult.Loading -> {
                    _state.value = HomeScreenUiStates.LoadingScreen
                }

                NetworkResult.NetworkError -> {
                    _state.value = HomeScreenUiStates.FailedRequest
                }
            }
        }
    }

    fun showDashboard() {
        viewModelScope.launch {
            _state.value = HomeScreenUiStates.Dashboard
        }
    }

    fun showSearchBar() {
        viewModelScope.launch {
            _searchBarState.value = true
        }
    }

    fun hideSearchBar() {
        viewModelScope.launch {
            _searchBarState.value = false
        }
    }
}

sealed class HomeScreenUiStates {
    data object LandingScreen : HomeScreenUiStates()
    data object LoadingScreen : HomeScreenUiStates()
    data object FailedRequest : HomeScreenUiStates()
    data class SuccessQuery(val data: List<Recipe>) : HomeScreenUiStates()
    data object Dashboard : HomeScreenUiStates()
}


data class DashboardData(
    val featuredRecipes: List<Recipe> = listOf(
        Recipe(
            id = 1956998,
            title = "Tom Collins",
            imageUrl = "https://img.spoonacular.com/recipes/1956998-312x231.jpg",
            imageType = "jpg",
            description = "A refreshing cocktail."
        ),
        Recipe(
            id = 635032,
            title = "Bitter Gourd Boiled With Pork Ribs (Ma-Ra Tom Ka-Dook Mou)",
            imageUrl = "https://img.spoonacular.com/recipes/635032-312x231.jpg",
            imageType = "jpg",
            description = "A flavorful dish with bitter gourd and pork ribs."
        ),
        Recipe(
            id = 633852,
            title = "baked tomatoes",
            imageUrl = "https://img.spoonacular.com/recipes/633852-312x231.jpg",
            imageType = "jpg",
            description = "Simple and delicious baked tomatoes."
        ),
        Recipe(
            id = 663588,
            title = "Tomato Cutlets",
            imageUrl = "https://img.spoonacular.com/recipes/663588-312x231.jpg",
            imageType = "jpg",
            description = "Savory tomato cutlets."
        ),
        Recipe(1, "Pasta Primavera", "Delicious spring pasta.", ""),
        Recipe(2, "Chicken Curry", "Spicy and flavorful chicken curry.", "")
    ),
    val categories: List<String> = listOf(
        "Lunch",
        "Dinner",
        "Vegetarian",
        "Vegan",
        "Breakfast",
        "Salad",
        "Soup",
        "Pizza",
        "Burgers" // Added new categories
    ),
    val popularRecipes: List<Recipe> = listOf(
        Recipe(
            id = 652602,
            title = "Murgh Tandoori",
            imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
            imageType = "jpg",
            description = "Classic Indian tandoori chicken."
        ),
        Recipe(
            id = 641904,
            title = "Easy Chicken Tandoori",
            imageUrl = "https://img.spoonacular.com/recipes/641904-312x231.jpg",
            imageType = "jpg",
            description = "A simple and tasty chicken tandoori recipe."
        ),
        Recipe(
            id = 647867,
            title = "Indian Tandoori Chicken",
            imageUrl = "https://img.spoonacular.com/recipes/647867-312x231.jpg",
            imageType = "jpg",
            description = "Authentic Indian tandoori chicken."
        ),
        Recipe(
            id = 655712,
            title = "Peppery, Tangy Corn Salad",
            imageUrl = "https://img.spoonacular.com/recipes/655712-312x231.jpg",
            imageType = "jpg",
            description = "A refreshing and flavorful corn salad."
        ),
        Recipe(3, "Chocolate Lava Cake", "Warm and gooey chocolate cake.", ""),
        Recipe(4, "Beef Tacos", "Classic beef tacos with all the fixings.", "")
    )
)
