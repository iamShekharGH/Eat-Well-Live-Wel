package com.shekharhandigol.domain

import com.shekharhandigol.NoInputUseCase
import com.shekharhandigol.NoOutputUseCase
import com.shekharhandigol.ThemeRepository
import com.shekharhandigol.core.ThemeNames
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoInputUseCase<Flow<ThemeNames>> {

    override suspend fun invoke(): Flow<ThemeNames> {
        return themeRepository.getCurrentTheme()
    }
}

class SetCurrentThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoOutputUseCase<ThemeNames> {

    override suspend fun invoke(input: ThemeNames) {
        themeRepository.setCurrentTheme(input)
    }
}

class GetFirstLaunchStateUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoInputUseCase<Flow<Boolean>> {

    override suspend fun invoke(): Flow<Boolean> {
        return themeRepository.getFirstLaunchState()
    }
}

class SetFirstLaunchStateUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoOutputUseCase<Boolean> {
    override suspend fun invoke(input: Boolean) {
        themeRepository.setFirstLaunchState(input)
    }

}

class GetUserNameUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoInputUseCase<Flow<String>> {
    override suspend fun invoke(): Flow<String> {
        return themeRepository.getUserName()
    }
}

class SaveUserNameUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoOutputUseCase<String> {
    override suspend fun invoke(input: String) {
        themeRepository.saveUserName(input)
    }

}