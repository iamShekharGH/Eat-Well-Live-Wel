package com.shekharhandigol.features.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shekharhandigol.features.R

@Composable
fun OnboardingScreen(
    screenNo: Int,
    goBack: () -> Unit = {},
    goFront: () -> Unit = {},
) {
    val pageInfo = getPageInfo(screenNo)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(
            text = pageInfo.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )

        Box(modifier = Modifier.size(200.dp)) {
            Image(
                painterResource(pageInfo.image),
                contentDescription = "",
                modifier = Modifier
                    .width(400.dp)
                    .height(400.dp)
                    /*.pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale *= zoom
                            offset += pan
                        }
                    }*/
                    .graphicsLayer(
                        scaleX = 2f,
                        scaleY = 2f
                    ),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            text = pageInfo.description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = {
                goBack.invoke()
            }) {
                Text(text = "< Back", style = MaterialTheme.typography.labelLarge)
            }


            Button(onClick = {
                goFront.invoke()
            }) {
                Text(text = "Next >", style = MaterialTheme.typography.labelLarge)
            }
        }

    }
}

fun getPageInfo(screenNo: Int): OnboardingPageDataClass {

    when (screenNo) {
        0 -> {
            return OnboardingPageDataClass(
                title = "Discover New Recipes",
                description = "Explore thousands of recipes from around the world.",
                image = R.drawable.one
            )
        }

        1 -> {
            return OnboardingPageDataClass(
                title = "Plan Your Meals",
                description = "Create custom meal plans for the week with a click.\" placed below the title.",
                image = R.drawable.two
            )
        }

        2 -> {
            return OnboardingPageDataClass(
                title = "Generate Grocery List\'s at the top center.",
                description = "Automatically create a grocery list based on your meal plan.",
                image = R.drawable.three
            )

        }

        else -> {
            return OnboardingPageDataClass(
                title = "Discover New Recipes",
                description = "Explore thousands of recipes from around the world.",
                image = R.drawable.one
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(2, {}, {})
}

/*
Page 1:

Title: "Discover New Recipes" at the top center.
Description: "Explore thousands of recipes from around the world." placed just below the title.
Image: A large image (or Lottie animation) of various recipe cards placed in the middle.
Buttons:
Next button at the bottom right ("Next" in bold) to go to the next page.
Skip button at the bottom left, allowing the user to skip onboarding and go to the Home Screen.
Page 2:

Title: "Plan Your Meals" at the top center.
Description: "Create custom meal plans for the week with a click." placed below the title.
Image: A meal planner calendar image in the center.
Buttons:
Next button at the bottom right.
Back button at the bottom left.
Page 3:

Title: "Generate Grocery Lists" at the top center.
Description: "Automatically create a grocery list based on your meal plan." placed below the title.
Image: A grocery list animation or image.
Buttons:
Get Started button at the center bottom, which completes the onboarding process and navigates to the Home Screen.
Back button at the bottom left.
 */