package com.shekharhandigol.features.detailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.shekharhandigol.core.models.uiModels.WineProduct
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.R
import com.shekharhandigol.features.util.capitalizeFirstLetter


@Composable
fun ProductMatchesItem(wineProduct: WineProduct) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = wineProduct.imageUrl,
                    contentDescription = wineProduct.title,
                    modifier = Modifier.size(64.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.image_error)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    Text(
                        text = wineProduct.title.capitalizeFirstLetter(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = wineProduct.price,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = wineProduct.description.capitalizeFirstLetter(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,

            )
        }
    }
}

@ModePreview
@Composable
fun PreviewProductMatchesItem() {
    ProductMatchesItem(
        WineProduct(
            id = 1,
            title = "Chateau Margaux",
            description = "A classic Bordeaux wine with rich flavors and aromas.",
            price = "$150.00",
            imageUrl = "https://example.com/chateau_margaux.jpg",
            averageRating = 4.8,
            link = ""

        )
    )
}