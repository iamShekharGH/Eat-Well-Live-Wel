package com.shekharhandigol.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.shekharhandigol.core.ThemeNames
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatastoreDAO @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val themeKey = stringPreferencesKey(DATASTORE_STRING_THEME_KEY)
    private val onboardingKey = stringPreferencesKey(DATASTORE_STRING_ONBOARDING_KEY)

    val themeState: Flow<ThemeNames> = dataStore.data.map { preferences ->
        preferences[themeKey]?.let { ThemeNames.valueOf(it) } ?: ThemeNames.LIGHT
    }

    suspend fun saveTheme(names: ThemeNames) {
        dataStore.edit { preferences ->
            preferences[themeKey] = names.name
        }
    }

    val onboardingState: Flow<Boolean> = dataStore.data.map { preferences ->
        (preferences[onboardingKey] ?: true) as Boolean

    }

    suspend fun saveOnboardingState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[onboardingKey] = state.toString()
        }
    }
}
