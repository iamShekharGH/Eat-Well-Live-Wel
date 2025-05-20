package com.shekharhandigol.domain

import com.shekharhandigol.ThemeRepository
import com.shekharhandigol.UseCase
import com.shekharhandigol.core.ThemeNames
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : UseCase<Unit, Flow<ThemeNames>> {

    override suspend fun invoke(input: Unit): Flow<ThemeNames> {
        return themeRepository.getCurrentTheme()
    }
}

class SetCurrentThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : UseCase<ThemeNames, Unit> {

    override suspend fun invoke(input: ThemeNames) {
        themeRepository.setCurrentTheme(input)
    }
}