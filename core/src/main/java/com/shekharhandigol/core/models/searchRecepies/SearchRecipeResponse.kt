package com.shekharhandigol.core.models.searchRecepies

import kotlinx.serialization.Serializable

@Serializable
data class SearchRecipeResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
) {
    @Serializable
    data class Result(
        val id: Int,
        val image: String,
        val imageType: String,
        val title: String
    )
}