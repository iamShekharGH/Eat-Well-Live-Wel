package com.shekharhandigol.features.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.SearchResultScreen
import com.shekharhandigol.features.homeScreen.FailedRequestScreen

@Composable
fun MainFavouriteScreen() {
    val viewModels: MainFavouriteScreenViewModel = hiltViewModel()
    viewModels.getRecipesFromDb()
    val recipes = viewModels.recipes.collectAsStateWithLifecycle()
    FavouriteScreen(recipes.value)

}

@Composable
fun FavouriteScreen(recipes: List<Recipe>) {
    if (recipes.isEmpty()) {
        FailedRequestScreen()
    } else
        Column {
            Text(
                text = "Favourite Screen",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "No of items in the list ${recipes.size}",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
            ) {
                items(recipes.size) {
                    SearchResultScreen(recipes[it], {}, {})
                }

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
        )
    )
}

@ModePreview
@Composable
fun PreviewFavouriteEmptyScreen() {
    FavouriteScreen(
        recipes = emptyList()
    )
}