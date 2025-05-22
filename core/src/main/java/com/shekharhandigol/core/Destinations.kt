package com.shekharhandigol.core

import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations {
    @Serializable
    data object Onboarding : Destinations()

    @Serializable
    data object FirstOnboardingScreen : Destinations()

    @Serializable
    data object HomeScreen : Destinations()

    @Serializable
    data object MainHomeScreen : Destinations()

    @Serializable
    data class MainRecipeDetailScreen(val id: Int) : Destinations()

    @Serializable
    data object SettingsScreen : Destinations()



}