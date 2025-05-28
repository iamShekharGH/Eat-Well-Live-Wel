package com.shekharhandigol.eatwelllivewell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.core.ui.theme.EatWellLiveWellTheme
import com.shekharhandigol.eatwelllivewell.ui.EatWellLiveWellNavHost
import com.shekharhandigol.features.homeScreen.LandingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val viewModel: MainActivityViewModel by viewModels()

        splashScreen.setKeepOnScreenCondition(
            condition = {
                !viewModel.isDataReady.value
            }
        )

        setContent {
            val currentTheme = when (val themeState =
                viewModel.currentThemeState.collectAsStateWithLifecycle().value) {
                is UiLoadState.Success -> themeState.data
                else -> ThemeNames.LIGHT
            }
            val onboardingState = when (val onboarding =
                viewModel.onboardingState.collectAsStateWithLifecycle().value) {
                is UiLoadState.Success -> onboarding.data
                else -> false
            }
            val userName =
                when (val nameState = viewModel.userNameState.collectAsStateWithLifecycle().value) {
                    is UiLoadState.Success -> nameState.data
                    else -> ""
                }
            val isDataReady = viewModel.isDataReady.collectAsStateWithLifecycle().value

            if (isDataReady) {
                EatWellLiveWellTheme(currentTheme = currentTheme) {
                    val navController = rememberNavController()
                    EatWellLiveWellNavHost(
                        navController,
                        onboardingState,
                        userName
                    )
                }
            } else {
                LandingScreen()
            }

        }
    }
}
