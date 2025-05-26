package com.shekharhandigol.core.models.uiModels

data class Recipe(
    val id: Int = -1,
    val title: String,
    val imageUrl: String,
    val imageType: String = "",
    val description: String = "",

    val favourite: Boolean = false,
    val tags: List<String> = listOf()
)
