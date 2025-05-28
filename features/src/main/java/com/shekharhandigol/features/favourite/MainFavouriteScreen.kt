package com.shekharhandigol.features.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.R
import com.shekharhandigol.features.SearchResultCard

@Composable
fun MainFavouriteScreen(navigateToDetailsScreen: (Int) -> Unit) {
    val viewModels: MainFavouriteScreenViewModel = hiltViewModel()
    viewModels.getRecipesFromDb()
    val recipes = viewModels.recipes.collectAsStateWithLifecycle()
    if (recipes.value.isEmpty()) {
        NoFavouritesAdded()
    } else
        FavouriteScreen(
            recipes = recipes.value,
            removeFromFav = { id, setFav ->
                viewModels.setItemToFav(id, setFav)
            },
            navigateToDetailsScreen
        )

}

@Composable
fun FavouriteScreen(
    recipes: List<Recipe>,
    removeFromFav: (Int, Boolean) -> Unit,
    navigateToDetailsScreen: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Text(
            text = "Favourite Screen",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "No of items in the list ${recipes.size}",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
        ) {
            items(recipes.size) {
                SearchResultCard(recipes[it], navigateToDetailsScreen, removeFromFav)
            }
        }
    }
}

@ModePreview
@Composable
fun NoFavouritesAdded() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.background,

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "Please Add favourite recipes.",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@ModePreview
@Composable
fun PreviewFavouriteScreen() {
    FavouriteScreen(
        recipes = listOf(
            Recipe(
                id = 652602,
                imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
                imageType = "jpg",
                title = "Murgh Tandoori"
            ),
            Recipe(
                id = 652602,
                imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
                imageType = "jpg",
                title = "Murgh Tandoori"
            ),
            Recipe(
                id = 652602,
                imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
                imageType = "jpg",
                title = "Murgh Tandoori"
            ),
            Recipe(
                id = 652602,
                imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
                imageType = "jpg",
                title = "Murgh Tandoori"
            ),
            Recipe(
                id = 652602,
                imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
                imageType = "jpg",
                title = "Murgh Tandoori"
            ),
            Recipe(
                id = 652602,
                imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
                imageType = "jpg",
                title = "Murgh Tandoori"
            )
        ),
        removeFromFav = { _, _ -> },
        navigateToDetailsScreen = {}
    )
}
