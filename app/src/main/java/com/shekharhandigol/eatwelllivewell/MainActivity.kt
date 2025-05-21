package com.shekharhandigol.eatwelllivewell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.shekharhandigol.core.ui.theme.EatWellLiveWellTheme
import com.shekharhandigol.eatwelllivewell.ui.EatWellLiveWellNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainActivityViewModel by viewModels()

        setTheme(R.style.Theme_EatWellLiveWell)
        enableEdgeToEdge()
        setContent {
            val currentTheme = viewModel.currentTheme.collectAsStateWithLifecycle()
            EatWellLiveWellTheme(currentTheme = currentTheme.value) {
                val navController = rememberNavController()
                EatWellLiveWellNavHost(navController)
            }
        }
    }
}
