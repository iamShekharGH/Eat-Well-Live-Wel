package com.shekharhandigol.features.homeScreen

import MainHomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shekharhandigol.core.Destinations

fun NavGraphBuilder.homeScreenNavigation() {
    navigation<Destinations.HomeScreen>(startDestination = Destinations.MainHomeScreen) {
        composable<Destinations.MainHomeScreen> {
            MainHomeScreen()
        }
    }
}