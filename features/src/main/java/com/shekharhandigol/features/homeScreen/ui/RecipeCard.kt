package com.shekharhandigol.features.homeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.R


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


@ModePreview
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

@ModePreview
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