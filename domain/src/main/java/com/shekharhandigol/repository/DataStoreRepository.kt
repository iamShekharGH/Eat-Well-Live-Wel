package com.shekharhandigol.repository

import com.shekharhandigol.core.ThemeNames
import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    fun getCurrentTheme(): Flow<ThemeNames>

    suspend fun setCurrentTheme(theme: ThemeNames)

    fun getFirstLaunchState(): Flow<Boolean>

    suspend fun setFirstLaunchState(state: Boolean)

    fun getUserName(): Flow<String>

    suspend fun saveUserName(userName: String)
}