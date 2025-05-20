package com.shekharhandigol.features.homeScreen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.shekharhandigol.core.Destinations
import com.shekharhandigol.features.detailScreen.MainRecipeDetailScreen

fun NavGraphBuilder.homeScreenNavigation(
    navigateToDetailsScreen: (Int) -> Unit,
    gotoSettings: () -> Unit = {},
    gotoProfile: () -> Unit = {},
    gotoFavourite: () -> Unit = {}
) {
    navigation<Destinations.HomeScreen>(startDestination = Destinations.MainHomeScreen) {
        composable<Destinations.MainHomeScreen> {
            MainHomeScreen(
                openDetailsScreen = { id ->
                navigateToDetailsScreen(id)
                },
                gotoSettings = { gotoSettings() },
                gotoProfile = { gotoProfile() },
                gotoFavourite = { gotoFavourite() }
            )
        }
        composable<Destinations.MainRecipeDetailScreen> { backStackEntry ->
            val arg: Destinations.MainRecipeDetailScreen = backStackEntry.toRoute()
            MainRecipeDetailScreen(arg.id)
        }
    }
}