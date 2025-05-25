package com.shekharhandigol.usecases

import android.util.Log.e
import com.shekharhandigol.NoInputUseCase
import com.shekharhandigol.NoOutputUseCase
import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.core.network.UiLoadState
import com.shekharhandigol.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetCurrentThemeUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : NoInputUseCase<Flow<UiLoadState<ThemeNames>>> {

    override suspend fun invoke(): Flow<UiLoadState<ThemeNames>> {
        return datastoreRepository.getCurrentTheme()
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
    private val datastoreRepository: DatastoreRepository
) : NoOutputUseCase<ThemeNames> {

    override suspend fun invoke(input: ThemeNames) {
        datastoreRepository.setCurrentTheme(input)
    }
}

class GetFirstLaunchStateUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : NoInputUseCase<Flow<UiLoadState<Boolean>>> {

    override suspend fun invoke(): Flow<UiLoadState<Boolean>> {
        return datastoreRepository.getFirstLaunchState()
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
    private val datastoreRepository: DatastoreRepository
) : NoOutputUseCase<Boolean> {
    override suspend fun invoke(input: Boolean) {
        datastoreRepository.setFirstLaunchState(input)
    }

}

class GetUserNameUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : NoInputUseCase<Flow<UiLoadState<String>>> {
    override suspend fun invoke(): Flow<UiLoadState<String>> {
        return datastoreRepository.getUserName()
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
    private val datastoreRepository: DatastoreRepository
) : NoOutputUseCase<String> {
    override suspend fun invoke(input: String) {
        datastoreRepository.saveUserName(input)
    }

}