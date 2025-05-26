package com.shekharhandigol.usecases

import com.shekharhandigol.NoInputUseCase
import com.shekharhandigol.core.models.uiModels.Recipe
import com.shekharhandigol.repository.RoomDbRepository
import javax.inject.Inject

class GetLocalRecipesUseCase @Inject constructor(
    private val roomDbRepository: RoomDbRepository
) : NoInputUseCase<List<Recipe>> {

    override suspend fun invoke(): List<Recipe> {
        return roomDbRepository.getRecipes()
    }
}

