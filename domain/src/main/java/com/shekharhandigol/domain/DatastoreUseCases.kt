package com.shekharhandigol.domain

import com.shekharhandigol.NoInputUseCase
import com.shekharhandigol.NoOutputUseCase
import com.shekharhandigol.ThemeRepository
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.network.UiLoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoInputUseCase<Flow<UiLoadState<ThemeNames>>> {

    override suspend fun invoke(): Flow<UiLoadState<ThemeNames>> = flow {
        emit(UiLoadState.Loading)

        themeRepository.getCurrentTheme().collect { themeName ->
            emit(UiLoadState.Success(themeName))
        }
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
) : NoInputUseCase<Flow<UiLoadState<Boolean>>> {

    override suspend fun invoke(): Flow<UiLoadState<Boolean>> = flow {
        emit(UiLoadState.Loading)

        themeRepository.getFirstLaunchState().collect { isFirstLaunch ->
            emit(UiLoadState.Success(isFirstLaunch))
        }
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
) : NoInputUseCase<Flow<UiLoadState<String>>> {
    override suspend fun invoke(): Flow<UiLoadState<String>> = flow {
        emit(UiLoadState.Loading)

        themeRepository.getUserName().collect { userName ->
            emit(UiLoadState.Success(userName))
        }
    }
}

class SaveUserNameUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoOutputUseCase<String> {
    override suspend fun invoke(input: String) {
        themeRepository.saveUserName(input)
    }

}