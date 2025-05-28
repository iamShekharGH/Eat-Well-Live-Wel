package com.shekharhandigol.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.ui.theme.ModePreview

@Composable
fun MainSearchScreen(
    listOfRecipes: List<Recipe>,
    openDetailsScreen: (Int) -> Unit,
    setItemFav: (Int, Boolean) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(
            horizontal = 8.dp,
            vertical = 8.dp
        )
    ) {
        items(listOfRecipes.size) {
            SearchResultCard(listOfRecipes[it], openDetailsScreen, setItemFav)
        }
    }

}

@Composable
fun SearchResultCard(
    recipe: Recipe,
    openDetailsScreen: (Int) -> Unit,
    setItemFav: (Int, Boolean) -> Unit
) {
    val circularProgressState = remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.padding(4.dp),
        onClick = {
            openDetailsScreen(recipe.id)
        }) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier.size(70.dp)
                ) {
                    if (circularProgressState.value) CircularProgressIndicator(
                        modifier = Modifier
                            .padding(20.dp)
                            .size(70.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipe.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        modifier = Modifier.size(70.dp),
                        contentScale = ContentScale.FillHeight,
                        onLoading = {
                            circularProgressState.value = true
                        },
                        onError = {
                            circularProgressState.value = false
                        },
                        onSuccess = {
                            circularProgressState.value = false
                        },
                        error = painterResource(R.drawable.placeholder),
                        placeholder = painterResource(R.drawable.placeholder)
                    )


                }
                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2
                )
            }

            IconButton(
                onClick = {
                    setItemFav(recipe.id, !recipe.favourite)
                },
                modifier = Modifier.align(alignment = Alignment.TopEnd)
            ) {
                if (recipe.favourite)
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Item is favourite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                else Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "Item is not favourite",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }

    }
}

@ModePreview
@Composable
fun PreviewSearchResultCard() {
    SearchResultCard(
        recipe = Recipe(
            id = 652602,
            imageUrl = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
            imageType = "jpg",
            title = "Murgh Tandoori"
        ),
        openDetailsScreen = {},
        setItemFav = { _, _ -> }
    )
}

@ModePreview
@Composable
fun PreviewMainSearchScreen() {
    MainSearchScreen(
        listOfRecipes = listOf(
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
        openDetailsScreen = {},
        setItemFav = { _, _ -> }
    )
}