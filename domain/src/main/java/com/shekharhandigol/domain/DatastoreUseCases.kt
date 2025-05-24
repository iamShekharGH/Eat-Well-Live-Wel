package com.shekharhandigol.domain

import android.util.Log.e
import com.shekharhandigol.NoInputUseCase
import com.shekharhandigol.NoOutputUseCase
import com.shekharhandigol.ThemeRepository
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.network.UiLoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetCurrentThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) : NoInputUseCase<Flow<UiLoadState<ThemeNames>>> {

    override suspend fun invoke(): Flow<UiLoadState<ThemeNames>> {
        return themeRepository.getCurrentTheme()
            .map<ThemeNames, UiLoadState<ThemeNames>> { themeName ->
                UiLoadState.Success(themeName)
            }.catch { error ->
                e("GetCurrentThemeUseCase", "Error collecting theme", error)
                emit(UiLoadState.Failure)
            }.onStart {
                emit(UiLoadState.Loading)
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

    override suspend fun invoke(): Flow<UiLoadState<Boolean>> {
        return themeRepository.getFirstLaunchState()
            .map<Boolean, UiLoadState<Boolean>> { isFirstLaunch ->
                UiLoadState.Success(isFirstLaunch)
            }.catch { error ->
                e("GetFirstLaunchStateUseCase", "Error collecting first launch state", error)
                emit(UiLoadState.Failure)
            }.onStart {
                emit(UiLoadState.Loading)
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
    override suspend fun invoke(): Flow<UiLoadState<String>> {
        return themeRepository.getUserName()
            .map<String, UiLoadState<String>> { userName ->
                if (userName.isBlank())
                    UiLoadState.Failure
                else
                    UiLoadState.Success(userName)
            }.catch { error ->
                e("GetUserNameUseCase", "Error collecting user name", error)
                emit(UiLoadState.Failure)
            }.onStart {
                emit(UiLoadState.Loading)
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