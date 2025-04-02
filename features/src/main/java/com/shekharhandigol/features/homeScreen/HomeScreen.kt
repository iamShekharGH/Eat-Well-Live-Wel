import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.features.R
import com.shekharhandigol.features.homeScreen.DashboardData
import com.shekharhandigol.features.homeScreen.HomeScreenUiStates
import com.shekharhandigol.features.homeScreen.HomeScreenViewModel
import com.shekharhandigol.features.homeScreen.LandingScreen
import com.shekharhandigol.features.homeScreen.LoadingScreen
import com.shekharhandigol.features.homeScreen.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen() {
    val vm: HomeScreenViewModel = hiltViewModel()
    val dashboardData = vm.dashboardData.collectAsStateWithLifecycle()
    val screenState = vm.state.collectAsStateWithLifecycle()
    var searchText = remember { "" }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { newValue ->
                                searchText = newValue
                                vm.getRecipes(newValue)
                            },
                            label = {
                                Text(
                                    text = "Search",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        )
                    }

                }
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
            when (screenState.value) {
                is HomeScreenUiStates.LandingScreen -> LandingScreen()
                HomeScreenUiStates.FailedRequest -> TODO()
                is HomeScreenUiStates.SuccessQuery -> {
                    val a = (screenState.value as HomeScreenUiStates.SuccessQuery).data
                    HomeScreen(dashboardData)
                }

                HomeScreenUiStates.LoadingScreen -> LoadingScreen()
            }
        }

    }


//    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun HomeScreen(dashboardData: State<DashboardData> = remember { mutableStateOf(DashboardData()) }) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Featured Recipes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
        )

        LazyRow(modifier = Modifier.padding(start = 8.dp)) {
            items(dashboardData.value.featuredRecipes.size) {
                RecipeCard(dashboardData.value.featuredRecipes[it])
            }
        }

        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
        )

        LazyRow(modifier = Modifier.padding(start = 8.dp)) {
            items(dashboardData.value.categories.size) {
                CategoriesTag(dashboardData.value.categories[it])

            }
        }
        Text(
            text = "Popular Recipes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold,
        )

        LazyRow {
            items(dashboardData.value.popularRecipes.size) {
                RecipeCard(dashboardData.value.popularRecipes[it])
            }
        }
    }


}

@Preview
@Composable
fun RecipeCard(
    recipes: Recipe = Recipe(
        name = "Chicken Curry",
        description = "Chicken Curry is a savory dish featuring tender chicken simmered in a flavorful,",
        imageUrl = "https://spoonacular.com/recipeImages/641859-312x231.jpg"
    )
) {

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .width(180.dp),
        ) {
            Image(painter = painterResource(id = R.drawable.two), contentDescription = "")
            Text(
                text = recipes.name, fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = recipes.description, style = MaterialTheme.typography.bodyMedium)

        }
    }

}

@Preview
@Composable
fun CategoriesTag(text: String = "Lunch") {
    Card(modifier = Modifier.padding(8.dp)) {
        Text(
            text = text, modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge
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