package com.shekharhandigol.features.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shekharhandigol.core.Destinations
import com.shekharhandigol.features.favourite.MainFavouriteScreen
import com.shekharhandigol.features.profile.MainProfileScreen

fun NavGraphBuilder.settingsNavigation(navigateToDetailsScreen: (Int) -> Unit) {
    composable<Destinations.SettingsScreen> {
        MainSettingsScreen()
    }
    composable<Destinations.ProfileScreen> {
        MainProfileScreen()
    }
    composable<Destinations.FavouriteScreen> {
        MainFavouriteScreen(navigateToDetailsScreen)
    }
}