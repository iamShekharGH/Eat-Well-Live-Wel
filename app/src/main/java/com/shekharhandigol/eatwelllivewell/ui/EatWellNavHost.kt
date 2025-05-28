package com.shekharhandigol.eatwelllivewell.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shekharhandigol.core.Destinations
import com.shekharhandigol.features.homeScreen.homeScreenNavigation
import com.shekharhandigol.features.onboarding.onboarding
import com.shekharhandigol.features.settings.settingsNavigation

@Composable
fun EatWellLiveWellNavHost(
    navHostController: NavHostController,
    firstLaunchState: Boolean,
    userName: String
) {
    NavHost(
        navController = navHostController,
        startDestination = if (firstLaunchState) Destinations.Onboarding else Destinations.HomeScreen
    ) {

        onboarding { navHostController.navigate(Destinations.HomeScreen) }
        homeScreenNavigation(
            navigateToDetailsScreen = { id ->
            navHostController.navigate(route = Destinations.MainRecipeDetailScreen(id))
            },
            gotoSettings = { navHostController.navigate(Destinations.SettingsScreen) },
            gotoProfile = { navHostController.navigate(Destinations.ProfileScreen) },
            gotoFavourite = { navHostController.navigate(Destinations.FavouriteScreen) },
            userName
        )
        settingsNavigation(
            navigateToDetailsScreen = { id ->
                navHostController.navigate(route = Destinations.MainRecipeDetailScreen(id))
            },
        )
    }
}

