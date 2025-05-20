package com.shekharhandigol

import com.shekharhandigol.core.ThemeNames
import com.shekharhandigol.storage.DatastoreDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val datastoreDAO: DatastoreDAO
) {
    fun getCurrentTheme(): Flow<ThemeNames> {
        return datastoreDAO.themeState
    }

    suspend fun setCurrentTheme(theme: ThemeNames) {
        datastoreDAO.saveTheme(theme)
    }
}