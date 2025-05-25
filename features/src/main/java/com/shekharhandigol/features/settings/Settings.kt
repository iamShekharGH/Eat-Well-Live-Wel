package com.shekharhandigol.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.ui.theme.EatWellLiveWellTheme
import com.shekharhandigol.core.ui.theme.ModePreview


@Composable
fun MainSettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadInitialSettings()
    }
    SettingsScreen(
        onThemeChange = viewModel::onThemeChange,
        onboardingState = viewModel::onboardingStateChanged,
        onUserNameChange = viewModel::onUsernameChange,
        currentTheme = viewModel.currentTheme.collectAsStateWithLifecycle().value,
        userName = viewModel.userName.collectAsStateWithLifecycle().value,
        currentOnboardingState = viewModel.onboardingState.collectAsStateWithLifecycle().value,
        isLoading = viewModel.loadingState.collectAsStateWithLifecycle().value
    )
}

@Composable
fun SettingsScreen(
    onThemeChange: (ThemeNames) -> Unit,
    onboardingState: (Boolean) -> Unit,
    onUserNameChange: (String) -> Unit,
    currentTheme: ThemeNames,
    userName: String,
    currentOnboardingState: Boolean,
    isLoading: Boolean,
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,


        ) {
        var dropdownItemsState by remember { mutableStateOf(false) }
        var onboardingItemsState by remember(currentOnboardingState) {
            mutableStateOf(
                currentOnboardingState
            )
        }
        var usernameState by remember(userName) { mutableStateOf(userName) }
        val loadingState by remember(isLoading) { mutableStateOf(false) }

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

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Show Onboarding Screen",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .clickable { onboardingItemsState = !onboardingItemsState }
            )
            Switch(
                checked = onboardingItemsState,
                onCheckedChange = {
                    onboardingItemsState = it
                    onboardingState(it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                thumbContent = if (onboardingItemsState) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Onboarding Enabled",
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Onboarding Disabled",
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                }
            )
        }

        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "User's Name",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = usernameState,
                onValueChange = { usernameState = it },
                label = { Text("Enter your name") },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        onUserNameChange(usernameState)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = ""
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))
        if (loadingState)
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.secondary
            )
    }
}


@ModePreview()
@Composable
fun SettingsScreenPreview() {
    EatWellLiveWellTheme {
        SettingsScreen(
            onThemeChange = {},
            onboardingState = {},
            onUserNameChange = {},
            currentTheme = ThemeNames.LIGHT,
            userName = "User",
            currentOnboardingState = false,
            isLoading = true
        )
    }
}
