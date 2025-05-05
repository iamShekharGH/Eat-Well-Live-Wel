package com.shekharhandigol.features.util


fun String?.capitalizeFirstLetter(): String {
    return if (this.isNullOrEmpty()) ""
    else this.replaceFirstChar {
        it.uppercase()
    }
}