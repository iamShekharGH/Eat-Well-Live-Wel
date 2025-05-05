package com.shekharhandigol.features.detailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.features.homeScreen.util.capitalizeFirstLetter
import com.shekharhandigol.features.homeScreen.util.recipeDetailDummy


@Composable
fun ProductMatchesItem(productMatch: RecipeDetailsResponse.WinePairing.ProductMatch) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = productMatch.imageUrl,
                    contentDescription = productMatch.title,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    Text(
                        text = productMatch.title.capitalizeFirstLetter(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = productMatch.price,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = productMatch.description.capitalizeFirstLetter(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
fun PreviewProductMatchesItem() {
    ProductMatchesItem(recipeDetailDummy.winePairing.productMatches[0])
}