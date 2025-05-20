package com.shekharhandigol.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MainSettingsScreen() {
    SettingsScreen()
}

@Composable
fun SettingsScreen(
    onThemeChange: (ThemeNames) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,


        ) {
        var dropdownItemsState by remember { mutableStateOf(true) }

        Text(
            text = "Settings Screen",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))
        Box {
            Text(
                text = "Select Theme",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = dropdownItemsState,
                onDismissRequest = { dropdownItemsState = false }

            ) {
                ThemeNames.entries.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.displayName) },
                        onClick = {
                            dropdownItemsState = false
                            onThemeChange(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

enum class ThemeNames(val displayName: String) {
    HIGH_CONTRAST_LIGHT("High Contrast Light"),
    HIGH_CONTRAST_DARK("High Contrast Dark"),
    MEDIUM_CONTRAST_LIGHT("Medium Contrast Light"),
    MEDIUM_CONTRAST_DARK("Medium Contrast Dark"),
    LIGHT("Light"), DARK("Dark")
}