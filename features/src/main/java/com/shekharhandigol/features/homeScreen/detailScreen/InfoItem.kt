package com.shekharhandigol.features.homeScreen.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun InfoItem(text: String) {
    Row(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Info Icon",
            modifier = Modifier
                .size(24.dp)
                .padding(end = 4.dp),
            tint = MaterialTheme.colorScheme.onTertiary
        )
        Text(text = text, color = MaterialTheme.colorScheme.onTertiary)
    }
}

@Preview
@Composable
fun PreviewInfoItem() {
    InfoItem("Info Item")
}