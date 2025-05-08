package com.shekharhandigol.eatwelllivewell.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shekharhandigol.core.Destinations
import com.shekharhandigol.features.homeScreen.homeScreenNavigation
import com.shekharhandigol.features.onboarding.onboarding

@Composable
fun EatWellLiveWellNavHost(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Destinations.HomeScreen) {

        onboarding { navHostController.navigate(Destinations.HomeScreen) }
        homeScreenNavigation{ id->
            navHostController.navigate(route = Destinations.MainRecipeDetailScreen(id))
        }


    }
}

