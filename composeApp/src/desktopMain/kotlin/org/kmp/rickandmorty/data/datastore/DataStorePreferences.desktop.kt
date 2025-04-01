package org.kmp.rickandmorty.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.kmp.rickandmorty.utils.dataStoreFileName

actual fun dataStorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore {
        dataStoreFileName
    }
}