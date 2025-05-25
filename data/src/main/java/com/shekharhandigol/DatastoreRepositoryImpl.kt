package com.shekharhandigol

import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.datastore.DatastoreDAO
import com.shekharhandigol.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DatastoreRepositoryImpl @Inject constructor(
    private val datastoreDAO: DatastoreDAO
) : DatastoreRepository {
    override fun getCurrentTheme(): Flow<ThemeNames> {
        return datastoreDAO.themeState
    }

    override suspend fun setCurrentTheme(theme: ThemeNames) {
        datastoreDAO.saveTheme(theme)
    }

    override fun getFirstLaunchState(): Flow<Boolean> {
        return datastoreDAO.onboardingState
    }

    override suspend fun setFirstLaunchState(state: Boolean) {
        datastoreDAO.saveOnboardingState(state)
    }

    override fun getUserName(): Flow<String> = datastoreDAO.userNameState
    override suspend fun saveUserName(userName: String) {
        datastoreDAO.saveUserName(userName)
    }
}