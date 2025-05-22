package com.shekharhandigol.features.homeScreen.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shekharhandigol.core.ui.theme.ModePreview


@Composable
fun CategoriesTag(text: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            text = text, modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )
    }
}

@ModePreview
@Composable
fun PreviewCategoriesTag(){
    CategoriesTag(text = "Lunch")
}