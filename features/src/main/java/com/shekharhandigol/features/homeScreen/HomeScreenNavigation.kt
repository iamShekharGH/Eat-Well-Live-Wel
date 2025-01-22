package com.shekharhandigol.features.homeScreen

import HomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shekharhandigol.core.Destinations

fun NavGraphBuilder.homeScreenNavigation() {
    navigation<Destinations.HomeScreen>(startDestination = Destinations.MainHomeScreen) {
        composable<Destinations.MainHomeScreen> {
            HomeScreen()
        }
    }
}