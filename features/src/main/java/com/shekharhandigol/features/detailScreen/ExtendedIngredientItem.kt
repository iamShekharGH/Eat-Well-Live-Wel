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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.shekharhandigol.core.models.recepieDetail.RecipeDetailsResponse
import com.shekharhandigol.features.R
import com.shekharhandigol.features.homeScreen.util.capitalizeFirstLetter
import com.shekharhandigol.features.homeScreen.util.recipeDetailDummy


@Composable
fun ExtendedIngredientItem(ingredient: RecipeDetailsResponse.ExtendedIngredient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            model = ingredient.image,
            contentDescription = ingredient.name,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(4.dp)),
            error = painterResource(R.drawable.image_error)
        )
        Column {
            Text(
                text = ingredient.name.capitalizeFirstLetter(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = ingredient.original.capitalizeFirstLetter(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun PreviewExtendedIngredientItem() {
    ExtendedIngredientItem(recipeDetailDummy.extendedIngredients[1])
}