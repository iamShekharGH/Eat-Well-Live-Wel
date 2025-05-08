package com.shekharhandigol.features.homeScreen

import MainHomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.shekharhandigol.core.Destinations
import com.shekharhandigol.features.detailScreen.MainRecipeDetailScreen

fun NavGraphBuilder.homeScreenNavigation(navigateToDetailsScreen: (Int) -> Unit) {
    navigation<Destinations.HomeScreen>(startDestination = Destinations.MainHomeScreen) {
        composable<Destinations.MainHomeScreen> {
            MainHomeScreen { id ->
                navigateToDetailsScreen(id)
            }
        }
        composable<Destinations.MainRecipeDetailScreen> { backStackEntry ->
            val arg: Destinations.MainRecipeDetailScreen = backStackEntry.toRoute()
            MainRecipeDetailScreen(arg.id)
        }
    }
}