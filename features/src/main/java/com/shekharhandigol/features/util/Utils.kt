package com.shekharhandigol.features.util

import com.shekharhandigol.features.BuildConfig


fun String?.capitalizeFirstLetter(): String {
    return if (this.isNullOrEmpty()) ""
    else this.replaceFirstChar {
        it.uppercase()
    }
}

const val spoonacularApiKey = BuildConfig.SPOONACULAR_API_KEY
