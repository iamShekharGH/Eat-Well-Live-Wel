package com.shekharhandigol.features.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.shekharhandigol.features.homeScreen.ui.TopAppBarContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(
    openDetailsScreen: (Int) -> Unit,
    gotoSettings: () -> Unit = {},
    gotoProfile: () -> Unit = {},
    gotoFavourite: () -> Unit = {}
) {
    val vm: HomeScreenViewModel = hiltViewModel()
    val dashboardData = vm.dashboardData.collectAsStateWithLifecycle()
    val screenState = vm.state.collectAsStateWithLifecycle()



    Scaffold(
        topBar = {
            TopAppBarContent(
                viewModel = vm,
                gotoSettings = gotoSettings,
                gotoProfile = gotoProfile,
                gotoFavourite = gotoFavourite
            )
        },
        /*snackbarHost = {

        },*/
        containerColor = MaterialTheme.colorScheme.background,

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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
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
                placeholder = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.placeholder)
            )

            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.4f))
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = recipes.title, fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                )
                Text(
                    text = recipes.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondaryContainer,
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
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = text, modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
    }
}
