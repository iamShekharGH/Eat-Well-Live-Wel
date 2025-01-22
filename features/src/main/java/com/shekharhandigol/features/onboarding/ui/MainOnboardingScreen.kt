package com.shekharhandigol.features.onboarding.ui

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun MainOnboardingScreen(navigate: () -> Unit) {

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()


    HorizontalPager(state = pagerState) { page ->

        OnboardingScreen(page, {
            if (pagerState.currentPage > 0) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        }, {
            if (pagerState.currentPage == 2) {
                navigate.invoke()
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        })

    }

}

data class OnboardingPageDataClass(
    val title: String,
    val description: String,
    val image: Int // Replace with your image resource IDs
)

@Preview
@Composable
fun PreviewMainOnboardingScreen() {
    MainOnboardingScreen({})
}