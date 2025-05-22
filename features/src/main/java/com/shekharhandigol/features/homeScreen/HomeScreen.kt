package com.shekharhandigol.features.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.core.ui.theme.EatWellLiveWellTheme
import com.shekharhandigol.core.ui.theme.ModePreview
import com.shekharhandigol.features.MainSearchScreen
import com.shekharhandigol.features.homeScreen.ui.CategoriesTag
import com.shekharhandigol.features.homeScreen.ui.RecipeCard
import com.shekharhandigol.features.homeScreen.ui.TopAppBarContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(
    openDetailsScreen: (Int) -> Unit,
    gotoSettings: () -> Unit = {},
    gotoProfile: () -> Unit = {},
    gotoFavourite: () -> Unit = {},
    userName: String
) {
    val vm: HomeScreenViewModel = hiltViewModel()
    val dashboardData = vm.dashboardData.collectAsStateWithLifecycle()
    val screenState = vm.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    //ShowSnackBar(snackbarHostState)

    Scaffold(
        topBar = {
            TopAppBarContent(
                viewModel = vm,
                gotoSettings = gotoSettings,
                gotoProfile = gotoProfile,
                gotoFavourite = gotoFavourite,
                userName = userName
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = MaterialTheme.colorScheme.background,

        ) { padding ->

        Column(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = 8.dp,
                    end = 8.dp
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (val state = screenState.value) {
                is HomeScreenUiStates.LandingScreen -> LandingScreen()
                HomeScreenUiStates.FailedRequest -> FailedRequestScreen()
                is HomeScreenUiStates.SuccessQuery -> {
                    MainSearchScreen(state.data, openDetailsScreen)
                }

                HomeScreenUiStates.LoadingScreen -> LoadingScreen()
                HomeScreenUiStates.Dashboard -> HomeScreen(dashboardData.value, openDetailsScreen)
            }
        }

    }
}

@Composable
fun ShowSnackBar(state: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            state.showSnackbar(
                message = "Currently Working On This Feature",
                actionLabel = "OK",
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )
        }
    }
}


@Composable
fun HomeScreen(
    dashboardData: DashboardData,
    openDetailsScreen: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        TitledLazyColumn(
            title = "Featured Recipes",
            items = dashboardData.featuredRecipes,
            itemContent = { RecipeCard(it, openDetailsScreen) }
        )

        TitledLazyColumn(
            title = "Categories",
            items = dashboardData.categories,
            itemContent = { CategoriesTag(it) }
        )

        TitledLazyColumn(
            title = "Popular Recipes",
            items = dashboardData.popularRecipes,
            itemContent = { RecipeCard(it, openDetailsScreen) }
        )
    }
}


@ModePreview()
@Composable
fun PreviewHomeScreen() {
    EatWellLiveWellTheme {
        HomeScreen(dashboardData = DashboardData(), openDetailsScreen = {})
    }
}

@Composable
fun <T> TitledLazyColumn(
    title: String,
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
        )
        LazyRow {
            items(items.size) {
                itemContent(items[it])
            }
        }
    }

}
