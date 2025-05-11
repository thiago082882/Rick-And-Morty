package org.kmp.rickandmorty.utils

interface AppPreferences {
    suspend fun getTheme(): String
    suspend fun changeThemeMode(value: String)
}