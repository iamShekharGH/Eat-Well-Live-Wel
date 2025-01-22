package com.shekharhandigol.features.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shekharhandigol.core.Destinations
import com.shekharhandigol.features.onboarding.ui.MainOnboardingScreen


fun NavGraphBuilder.onboarding(navigate: () -> Unit) {

    navigation<Destinations.Onboarding>(startDestination = Destinations.FirstOnboardingScreen) {
        composable<Destinations.FirstOnboardingScreen> {
            MainOnboardingScreen(navigate)
        }
    }
}
