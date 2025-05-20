package com.shekharhandigol.features.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.features.R
import com.shekharhandigol.features.homeScreen.FailedRequestScreen
import com.shekharhandigol.features.homeScreen.LoadingScreen
import com.shekharhandigol.features.util.recipeDetailDummy

@Composable
fun MainRecipeDetailScreen(id: Int) {

    val vm: MainRecipeDetailScreenViewModel = hiltViewModel()
    val recipeDetailScreenStateState = vm.detailScreenState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        vm.getRecipeDetails(id)
    }

    when (val state = recipeDetailScreenStateState.value) {
        RecipeDetailScreenState.Failed -> {
            FailedRequestScreen()
        }

        RecipeDetailScreenState.Loading -> {
            LoadingScreen()
        }

        is RecipeDetailScreenState.Success -> {
            RecipeDetailScreen(state.recipeDetailsResponse)
        }
    }
}

@Composable
fun RecipeDetailScreen(details: RecipeDetailsResponse) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)

    ) {
        item {
            AsyncImage(
                model = details.image,
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(40.dp)),
                fallback = painterResource(id = R.drawable.placeholder),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.image_error),
                contentScale = ContentScale.Crop,
            )
        }

        item {
            Text(
                text = details.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                InfoItem(text = "${details.servings} Servings")
                InfoItem(text = "${details.readyInMinutes}")
                InfoItem(text = "${details.healthScore}")
            }
        }

        item {
            Text(
                text = "Dish Type",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            LazyRow {
                items(details.dishTypes.size) {
                    DishTypeChip(text = details.dishTypes[it])
                }
            }
        }

        item {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(details.extendedIngredients.size) {
            ExtendedIngredientItem(ingredient = details.extendedIngredients[it])
        }

        item {
            Text(
                text = "Summary",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = details.summary,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }

        item {
            Text(
                text = "Wine Pairing",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = details.winePairing.pairingText,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }

        item {
            Text(
                text = "Recommended Wines",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(details.winePairing.pairedWines.size) {
            Text(
                text = "- ${details.winePairing.pairedWines[it]}",
                style = MaterialTheme.typography.bodyMedium
            )
        }


        item {
            Text(
                text = "Product Matches",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(details.winePairing.productMatches.size) {
            ProductMatchesItem(details.winePairing.productMatches[it])
        }
    }
}


@Preview
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(recipeDetailDummy)
}


