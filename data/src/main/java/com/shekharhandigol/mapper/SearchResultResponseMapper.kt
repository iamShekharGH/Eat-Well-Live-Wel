package com.shekharhandigol.mapper

import com.shekharhandigol.core.models.searchRecepies.SearchRecipeResponse
import com.shekharhandigol.core.models.uiModels.Recipe

fun SearchRecipeResponse.Result.toDomain(isFavorite: Boolean = false): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        imageUrl = this.image,
        imageType = this.imageType,
        description = "",
        favourite = isFavorite,
        tags = emptyList()
    )
}

fun SearchRecipeResponse.toDomainList(): List<Recipe> {
    return this.results.map { searchResultDto ->
//        val isCurrentItemFavorite = favoriteRecipeIds.contains(searchResultDto.id)
        searchResultDto.toDomain()
    }
}