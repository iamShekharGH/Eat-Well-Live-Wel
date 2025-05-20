package com.shekharhandigol.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject


class DatastoreDAO @Inject constructor(dataStore: DataStore<Preferences>) {

    fun setThemeState() {


    }
}