package com.shekharhandigol.features.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.network.NetworkResult
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.features.util.spoonacularApiKey
import com.shekharhandigol.usecases.GetRandomRecipesUseCase
import com.shekharhandigol.usecases.GetRecipeUseCase
import com.shekharhandigol.usecases.SearchRecipesLocallyUseCase
import com.shekharhandigol.usecases.SetItemToFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
    private val searchRecipesLocallyUseCase: SearchRecipesLocallyUseCase,
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val setItemToFavUseCase: SetItemToFavUseCase
) : ViewModel() {


    private val _state = MutableStateFlow<HomeScreenUiStates>(HomeScreenUiStates.Dashboard)
    val state = _state.asStateFlow()

    private val _dashboardData = MutableStateFlow(DashboardData())
    val dashboardData = _dashboardData.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchBarState = MutableStateFlow(false)
    val searchBarState = _searchBarState.asStateFlow()

    init {
        getDashboardData()

        viewModelScope.launch {
            _searchText
                .debounce(500L)
                .filter { query ->
                    val isNotBlank = query.isNotBlank()
                    val isLongEnough = query.length >= 3

                    if (!isNotBlank) {
                        if (_state.value !is HomeScreenUiStates.Dashboard) {
                            _state.value = HomeScreenUiStates.Dashboard
                        }
                        return@filter false
                    }
                    if (!isLongEnough && _state.value is HomeScreenUiStates.SuccessQuery) {
                        _state.value = HomeScreenUiStates.Dashboard
                        return@filter false
                    }
                    isNotBlank && isLongEnough
                }
                .distinctUntilChanged()
                .collectLatest { validQuery ->
                    _state.value = HomeScreenUiStates.LoadingScreen
                    getSearchRecipeResult(validQuery)
                }
        }
    }

    private fun getDashboardData() {
        viewModelScope.launch {
            getRandomRecipesUseCase(20)
                .combine(searchRecipesLocallyUseCase("")) { featuredRecipe, favouriteRecipe ->
                    _dashboardData.value = _dashboardData.value.copy(
                        featuredRecipes = featuredRecipe,
                        categories = dummyDataDashboard.categories,
                        popularRecipes = favouriteRecipe
                    )
                }.collect {
//                    _state.value = HomeScreenUiStates.Dashboard
                }
        }
    }

    fun emptySearchString() {
        _searchText.value = ""
    }

    //    @OptIn(FlowPreview::class)
    fun searchTextChanged(text: String) {
        _searchText.value = text

        /*viewModelScope.launch {
            searchText.debounce(500L)
                .filter { text.isNotBlank() }
                .filter { text.length >= 3 }
                .collectLatest { text ->
                    _state.value = HomeScreenUiStates.LoadingScreen
                    getSearchRecipeResult(text)
            }
        }*/
    }

    private suspend fun getSearchRecipeResult(query: String) {
        getRecipeUseCase(Pair(spoonacularApiKey, query)).collect { result ->
            when (result) {
                is UiLoadState.Success -> {
                    _state.value = HomeScreenUiStates.SuccessQuery(result.data)
                }

                UiLoadState.Failure -> {
                    _state.value = HomeScreenUiStates.FailedRequest
                }

                UiLoadState.Loading -> {
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
            _searchText.value = ""
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
        showDashboard()
    }

    fun addItemToFav(id: Int, setToFav: Boolean) {
        viewModelScope.launch {
            setItemToFavUseCase(Pair(id, setToFav))
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


val dummyDataDashboard = DashboardData(
    featuredRecipes = listOf(
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
    categories = listOf(
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
    popularRecipes = listOf(
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

data class DashboardData(
    val featuredRecipes: List<Recipe> = emptyList(),
    val categories: List<String> = emptyList(),
    val popularRecipes: List<Recipe> = emptyList()
)
