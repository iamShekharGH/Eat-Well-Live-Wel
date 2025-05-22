package com.shekharhandigol.features.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shekharhandigol.core.Destinations

fun NavGraphBuilder.settingsNavigation() {
    composable<Destinations.SettingsScreen> {
        MainSettingsScreen()
    }
}