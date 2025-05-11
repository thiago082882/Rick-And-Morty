package org.kmp.rickandmorty.utils


import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

class DataStoreAppPreferences(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    private val themeKey = stringPreferencesKey("theme")

    override suspend fun getTheme() = dataStore.data.map { preferences ->
        preferences[themeKey] ?: Theme.DARK_MODE.name
    }.first()


    override suspend fun changeThemeMode(value: String) {
        dataStore.edit { preferences ->
            preferences[themeKey] = value
        }
    }
}