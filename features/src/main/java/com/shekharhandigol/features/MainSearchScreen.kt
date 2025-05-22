package com.shekharhandigol.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.shekharhandigol.core.models.searchRecepies.SearchRecipeResponse
import com.shekharhandigol.core.ui.theme.ModePreview

@Composable
fun MainSearchScreen(a: SearchRecipeResponse, openDetailsScreen: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(
            horizontal = 8.dp,
            vertical = 8.dp
        )
    ) {
        items(a.results.size) {
            SearchResultCard(a.results[it], openDetailsScreen)
        }
    }

}

@Composable
fun SearchResultCard(
    result: SearchRecipeResponse.Result,
    openDetailsScreen: (Int) -> Unit
) {
    val circularProgressState = remember { mutableStateOf(true) }

    Card(modifier = Modifier.padding(4.dp),
        onClick = {
            openDetailsScreen(result.id)
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
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
                        .data(result.image)
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
                text = result.title,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2
            )
        }
    }
}

@ModePreview
@Composable
fun PreviewSearchResultCard() {
    SearchResultCard(
        result = SearchRecipeResponse.Result(
            id = 652602,
            image = "https://img.spoonacular.com/recipes/652602-312x231.jpg",
            imageType = "jpg",
            title = "Murgh Tandoori"
        ),
        openDetailsScreen = {}
    )
}