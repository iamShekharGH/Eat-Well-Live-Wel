package com.shekharhandigol

import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.datastore.DatastoreDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeRepository @Inject constructor(
    private val datastoreDAO: DatastoreDAO
) {
    fun getCurrentTheme(): Flow<ThemeNames> {
        return datastoreDAO.themeState
    }

    suspend fun setCurrentTheme(theme: ThemeNames) {
        datastoreDAO.saveTheme(theme)
    }

    fun getFirstLaunchState(): Flow<Boolean> {
        return datastoreDAO.onboardingState
    }

    suspend fun setFirstLaunchState(state: Boolean) {
        datastoreDAO.saveOnboardingState(state)
    }

    fun getUserName(): Flow<String> {
        return datastoreDAO.userNameState
    }

    suspend fun saveUserName(userName: String) {
        datastoreDAO.saveUserName(userName)
    }



}