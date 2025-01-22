package com.shekharhandigol.eatwelllivewell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.shekharhandigol.core.ui.theme.EatWellLiveWellTheme
import com.shekharhandigol.eatwelllivewell.ui.EatWellLiveWellNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_EatWellLiveWell)
        enableEdgeToEdge()
        setContent {
            EatWellLiveWellTheme {
                val navController = rememberNavController()
                EatWellLiveWellNavHost(navController)
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }
}

/*

   1. I have to add no toolbar in theme.
   2. Create nav host.
   3. Create Onboarding.



 */

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EatWellLiveWellTheme {
        Greeting("Android")
    }
}