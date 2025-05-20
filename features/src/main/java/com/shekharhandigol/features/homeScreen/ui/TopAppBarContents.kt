package com.shekharhandigol.features.homeScreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.features.homeScreen.HomeScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarContent(
    viewModel: HomeScreenViewModel,
    gotoSettings: () -> Unit = {},
    gotoProfile: () -> Unit = {},
    gotoFavourite: () -> Unit = {}
) {
    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val searchBarState = viewModel.searchBarState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val expanded = remember { mutableStateOf(false) }


    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if (searchBarState.value) {
                    AnimatedVisibility(searchBarState.value) {
                        OutlinedTextField(
                            value = searchText.value,
                            onValueChange = { newValue ->
                                viewModel.searchTextChanged(newValue)
                            },
                            label = {
                                Text(
                                    text = "Search",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    if (searchText.value.isNotEmpty()) {
                                        viewModel.emptySearchString()
                                    } else {
                                        viewModel.hideSearchBar()
                                        viewModel.showDashboard()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = ""
                                    )
                                }
                            }
                        )
                    }

                } else {
                    Text(
                        text = "Eat Well Live Well",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )

                }
            }
        },
        navigationIcon = {
            if (searchBarState.value)
                IconButton(onClick = {
                    viewModel.showDashboard()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
        },
        actions = {
            if (!searchBarState.value)
                IconButton(onClick = {
                    viewModel.showSearchBar()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Localized description"
                    )
                }
            IconButton(onClick = {
                expanded.value = !expanded.value
            }) {

                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    DropdownMenuItem(text = { Text(text = "Settings") }, onClick = {
                        expanded.value = false
                        gotoSettings()
                    })
                    DropdownMenuItem(text = { Text(text = "Profile") }, onClick = {
                        expanded.value = false
                        gotoProfile()
                    })
                    DropdownMenuItem(text = { Text(text = "Favourite") }, onClick = {
                        expanded.value = false
                        gotoFavourite()
                    })
                }

            }

        },
        scrollBehavior = scrollBehavior
    )
}