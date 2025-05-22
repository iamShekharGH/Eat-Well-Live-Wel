package com.shekharhandigol.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
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
    private val onboardingKey = booleanPreferencesKey(DATASTORE_STRING_ONBOARDING_KEY)
    private val userNameKey = stringPreferencesKey(DATASTORE_USER_NAME_KEY)

    val themeState: Flow<ThemeNames> = dataStore.data.map { preferences ->
        preferences[themeKey]?.let { ThemeNames.valueOf(it) } ?: ThemeNames.LIGHT
    }

    suspend fun saveTheme(names: ThemeNames) {
        dataStore.edit { preferences ->
            preferences[themeKey] = names.name
        }
    }

    val onboardingState: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[onboardingKey] != false
    }

    suspend fun saveOnboardingState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[onboardingKey] = state
        }
    }

    val userNameState: Flow<String> = dataStore.data.map { preferences ->
        preferences[userNameKey] ?: ""
    }

    suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[userNameKey] = name
        }
    }

}
