package com.shekharhandigol.core.ui.theme

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
annotation class ModePreview