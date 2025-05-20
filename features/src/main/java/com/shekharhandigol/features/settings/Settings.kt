package com.shekharhandigol.features.settings

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.core.ThemeNames


@Composable
fun MainSettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val currentTheme by viewModel.currentTheme.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getCurrentTheme()
    }
    SettingsScreen(onThemeChange = viewModel::onThemeChange, currentTheme)
}
@Composable
fun SettingsScreen(
    onThemeChange: (ThemeNames) -> Unit,
    currentTheme: ThemeNames
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,


        ) {
        var dropdownItemsState by remember { mutableStateOf(true) }

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

        Row {
            Text(
                text = "Select Theme",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { dropdownItemsState = true }
                    .weight(1f)
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = currentTheme.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { dropdownItemsState = true }
                )

                DropdownMenu(
                    expanded = dropdownItemsState,
                    onDismissRequest = { dropdownItemsState = false }

                ) {
                    ThemeNames.entries.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item.displayName,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                            onClick = {
                                dropdownItemsState = false
                                onThemeChange(item)
                            }
                        )
                    }
                }
            }

        }

        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "id:pixel_5"
)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onThemeChange = {}, currentTheme = ThemeNames.LIGHT)
}
