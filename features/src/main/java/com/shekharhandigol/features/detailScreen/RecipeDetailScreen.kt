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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.shekharhandigol.core.models.uiModels.CaloricBreakdownInfo
import com.shekharhandigol.core.models.uiModels.IngredientItem
import com.shekharhandigol.core.models.uiModels.RecipeDetails
import com.shekharhandigol.core.models.uiModels.RecipeNutrition
import com.shekharhandigol.core.models.uiModels.WineProduct
import com.shekharhandigol.core.models.uiModels.WineSuggestion
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.R
import com.shekharhandigol.features.homeScreen.FailedRequestScreen
import com.shekharhandigol.features.homeScreen.LoadingScreen

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
            RecipeDetailScreen(state.recipeDetails)
        }
    }
}

@Composable
fun RecipeDetailScreen(details: RecipeDetails) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)

    ) {
        item {
            AsyncImage(
                model = details.imageUrl,
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
            details.winePairing?.let { item ->
                Text(
                    text = "Wine Pairing",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.pairingText.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )
            }
        }

        item {
            Text(
                text = "Recommended Wines",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        items(details.winePairing?.pairedWines?.size ?: 0) {
            Text(
                text = "- ${details.winePairing?.pairedWines[it]}",
                style = MaterialTheme.typography.bodyMedium
            )
        }


        item {
            if (details.winePairing?.productMatches?.isNotEmpty() == true) {
                Text(
                    text = "Product Matches",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        details.winePairing?.productMatches?.let { productMatches ->
            if (productMatches.isNotEmpty()) {
                items(productMatches.size) { index ->
                    ProductMatchesItem(wineProduct = productMatches[index])
                }
            }
        }
    }
}


@ModePreview
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(
        RecipeDetails(
            id = 1,
            title = "Recipe Title",
            imageUrl = "https://example.com/recipe_image.jpg",
            summary = "This is a sample recipe summary.",
            instructions = "Sample instructions go here.",
            servings = 4,
            readyInMinutes = 30,
            healthScore = 85.0,
            dishTypes = listOf("Main Course", "Dinner"),
            extendedIngredients = listOf(
                IngredientItem(1, "Flour", "Flour", 2.0, "24", ""),
                IngredientItem(1, "Flour", "Flour", 2.0, "24", ""),
                IngredientItem(1, "Flour", "Flour", 2.0, "24", ""),
            ),
            winePairing = WineSuggestion(
                pairedWines = listOf("Chardonnay", "Pinot Noir"),
                pairingText = "Pairs well with light-bodied white wines or fruity red wines.",
                productMatches = listOf(
                    WineProduct(
                        id = 1,
                        title = "Sample Wine 1",
                        description = "A delightful Chardonnay.",
                        price = "$15.99",
                        imageUrl = "https://example.com/wine1.jpg",
                        averageRating = 4.5,
                        link = "link1"
                    ),
                    WineProduct(
                        id = 2,
                        title = "Sample Wine 2",
                        description = "A smooth Pinot Noir.",
                        price = "$20.50",
                        imageUrl = "https://example.com/wine2.jpg",
                        averageRating = 4.2,
                        link = "link2"
                    )
                )
            ),
            aggregateLikes = 120,
            sourceName = "Delicious Recipes",
            sourceUrl = "https://example.com/delicious-recipes",
            diets = listOf("Vegetarian", "Gluten-Free"),
            nutrition = RecipeNutrition(
                nutrients = emptyList(),
                caloricBreakdown = CaloricBreakdownInfo(
                    percentProtein = 25.0,
                    percentFat = 30.0,
                    percentCarbs = 45.0
                ),
                weightPerServing = "100"
            )
        )
    )
}


