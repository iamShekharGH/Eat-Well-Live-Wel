package com.shekharhandigol.features.detailScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.shekharhandigol.core.ui.theme.ModePreview


@Composable
fun DishTypeChip(text: String) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@ModePreview
@Composable
fun PreviewDishTypeChip() {
    DishTypeChip("Dish Type Chip")
}