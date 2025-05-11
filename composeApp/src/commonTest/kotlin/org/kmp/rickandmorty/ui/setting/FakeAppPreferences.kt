package org.kmp.rickandmorty.ui.setting

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.data.repository.LocalCharacterRepository
import org.kmp.rickandmorty.kmp.rickandmorty.data.repository.FakeCharacterDao
import org.kmp.rickandmorty.utils.AppPreferences
import org.kmp.rickandmorty.utils.DataStoreAppPreferences
import org.kmp.rickandmorty.utils.Theme


class FakeAppPreferences : AppPreferences {
    var currentTheme: String = Theme.LIGHT_MODE.name

    override suspend fun getTheme(): String = currentTheme

    override suspend fun changeThemeMode(value: String) {
        currentTheme = value
    }
}

class FakeLocalCharacterRepository : LocalCharacterRepository(FakeCharacterDao())
