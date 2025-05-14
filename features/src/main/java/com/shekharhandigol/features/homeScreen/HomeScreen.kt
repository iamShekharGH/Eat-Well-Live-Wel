import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.shekharhandigol.features.MainSearchScreen
import com.shekharhandigol.features.R
import com.shekharhandigol.features.homeScreen.DashboardData
import com.shekharhandigol.features.homeScreen.FailedRequestScreen
import com.shekharhandigol.features.homeScreen.HomeScreenUiStates
import com.shekharhandigol.features.homeScreen.HomeScreenViewModel
import com.shekharhandigol.features.homeScreen.LandingScreen
import com.shekharhandigol.features.homeScreen.LoadingScreen
import com.shekharhandigol.features.homeScreen.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(openDetailsScreen: (Int) -> Unit) {
    val vm: HomeScreenViewModel = hiltViewModel()
    val dashboardData = vm.dashboardData.collectAsStateWithLifecycle()
    val screenState = vm.state.collectAsStateWithLifecycle()
    val searchText = vm.searchText.collectAsStateWithLifecycle()
    val searchBarState = vm.searchBarState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AnimatedVisibility(searchBarState.value){
                            if (searchBarState.value) {
                                OutlinedTextField(
                                    value = searchText.value,
                                    onValueChange = { newValue ->
                                        vm.searchTextChanged(newValue)
                                    },
                                    label = {
                                        Text(
                                            text = "Search",
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = {
                                            if (searchText.value.isNotEmpty()) {
                                                vm.emptySearchString()
                                            } else {
                                                vm.hideSearchBar()
                                                vm.showDashboard()
                                            }
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Close,
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                )
                            } else {
                                Text(
                                    text = "Eat Well Live Well",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        vm.showDashboard()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    if (!searchBarState.value)
                        IconButton(onClick = {
                            vm.showSearchBar()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Localized description"
                            )
                        }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }

                },
                scrollBehavior = scrollBehavior
            )
        },
        /*snackbarHost = {

        },*/
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,

        ) { padding ->

        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = 8.dp,
                    end = 8.dp
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (val state = screenState.value) {
                is HomeScreenUiStates.LandingScreen -> LandingScreen()
                HomeScreenUiStates.FailedRequest -> FailedRequestScreen()
                is HomeScreenUiStates.SuccessQuery -> {
                    MainSearchScreen(state.data, openDetailsScreen)
                }

                HomeScreenUiStates.LoadingScreen -> LoadingScreen()
                HomeScreenUiStates.Dashboard -> HomeScreen(dashboardData.value, openDetailsScreen)
                HomeScreenUiStates.CurrentlyWorkingOn -> {

                }
            }
        }

    }
}

@Composable
fun HomeScreen(
    dashboardData: DashboardData,
    openDetailsScreen: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Featured Recipes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
        )

        LazyRow {
            items(dashboardData.featuredRecipes.size) {
                RecipeCard(dashboardData.featuredRecipes[it], openDetailsScreen)
            }
        }

        Text(
            text = "Categories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
        )

        LazyRow {
            items(dashboardData.categories.size) {
                CategoriesTag(dashboardData.categories[it])

            }
        }
        Text(
            text = "Popular Recipes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
        )

        LazyRow {
            items(dashboardData.popularRecipes.size) {
                RecipeCard(dashboardData.popularRecipes[it], openDetailsScreen)
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(dashboardData = DashboardData(), openDetailsScreen = {})
}



@Composable
fun RecipeCard(
    recipes: Recipe,
    openDetailsScreen: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(end = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = {
            openDetailsScreen(recipes.id)
        },
        border = CardDefaults.outlinedCardBorder(),

    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter,
            propagateMinConstraints = true,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipes.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.two),
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .background(color = Color.Black.copy(alpha = 0.5f))
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

//            Image(painter = painterResource(id = R.drawable.two), contentDescription = "")
                Text(
                    text = recipes.title, fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = recipes.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

            }
        }

    }

}


@Preview
@Composable
fun PreviewRecipeCard() {
    RecipeCard(
        recipes = Recipe(
            title = "Chicken Curry",
            description = "Chicken Curry is a savory dish featuring tender chicken simmered in a flavorful,",
            imageUrl = "https://spoonacular.com/recipeImages/641859-312x231.jpg"
        ),
        openDetailsScreen = {}
    )
}

@Preview
@Composable
fun PreviewRecipeCardSmall() {
    RecipeCard(
        recipes = Recipe(
            title = "Chicken Curry",
            description = "Chicken Curry",
            imageUrl = "https://spoonacular.com/recipeImages/641859-312x231.jpg"
        ),
        openDetailsScreen = { }
    )
}

@Preview
@Composable
fun CategoriesTag(text: String = "Lunch") {
    Card(modifier = Modifier.padding(8.dp)) {
        Text(
            text = text, modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

/*
Home Screen

Contents:

Search Bar: Positioned at the top, allowing users to search for recipes by keywords or ingredients.
Featured Section:
Title: "Featured Recipes" at the top of the section.
Carousel of Recipe Cards: Horizontally scrollable cards with images, recipe names, and short descriptions (e.g., prep time, calories).
Categories Section:
Title: "Browse by Category" below the featured section.
Category Chips: Buttons like "Breakfast," "Lunch," "Dinner," "Vegetarian," etc., that users can tap to filter recipes by category.
Popular Recipes:
Title: "Popular Recipes" at the top of the section.
Recipe List/Grid: A vertically scrollable list or grid of trending recipes with images, names, and ratings.
Buttons:

Recipe Cards: Clicking any recipe card takes the user to the Recipe Detail Screen.
Category Chips: Clicking on a category chip filters the recipes accordingly.
Navigation:

Bottom Navigation with options to go to:
Home (current screen)
Search
Favorites
Meal Planner
Profile
 */