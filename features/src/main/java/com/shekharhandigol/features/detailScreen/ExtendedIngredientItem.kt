package com.shekharhandigol.features.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.shekharhandigol.core.models.uiModels.IngredientItem
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.R
import com.shekharhandigol.features.util.capitalizeFirstLetter


@Composable
fun ExtendedIngredientItem(ingredient: IngredientItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            model = ingredient.imageUrl,
            contentDescription = ingredient.name,
            modifier = Modifier
                .size(58.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(4.dp)),
            error = painterResource(R.drawable.ingredients),
            placeholder = painterResource(R.drawable.ingredients)
        )
        Column {
            Text(
                text = ingredient.name.capitalizeFirstLetter(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = ingredient.originalString.capitalizeFirstLetter(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@ModePreview
@Composable
fun PreviewExtendedIngredientItem() {
    ExtendedIngredientItem(
        IngredientItem(
            name = "Sugar",
            imageUrl = "",
            originalString = "1 cup sugar",
            amount = 1.0,
            unit = "cup",
            id = 1
        )
    )
}