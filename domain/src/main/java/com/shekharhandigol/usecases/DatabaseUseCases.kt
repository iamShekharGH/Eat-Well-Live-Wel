package com.shekharhandigol.usecases

import com.shekharhandigol.NoInputUseCase
import com.shekharhandigol.UseCase
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.repository.RoomDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalRecipesUseCase @Inject constructor(
    private val roomDbRepository: RoomDbRepository
) : NoInputUseCase<List<Recipe>> {

    override suspend fun invoke(): List<Recipe> {
        return roomDbRepository.getRecipes()
    }
}

class SearchRecipesLocallyUseCase @Inject constructor(
    private val roomDbRepository: RoomDbRepository
) : UseCase<String, Flow<List<Recipe>>> {

    override suspend fun invoke(query: String): Flow<List<Recipe>> {
        return roomDbRepository.searchRecipes(query = query)
    }
}

class GetRandomRecipesUseCase @Inject constructor(
    private val roomDbRepository: RoomDbRepository
) : UseCase<Int, Flow<List<Recipe>>> {

    override suspend fun invoke(limit: Int): Flow<List<Recipe>> {
        return roomDbRepository.getRandomRecipes(limit)
    }
}


