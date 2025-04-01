package org.kmp.rickandmorty.di


import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.kmp.rickandmorty.data.database.CharactersDatabase
import org.kmp.rickandmorty.data.datastore.dataStorePreferences
import org.kmp.rickandmorty.utils.AppPreferences
import org.kmp.rickandmorty.utils.getDatabaseBuilder
import org.koin.dsl.module

val databaseModule = module {

    // database
    single {
        getRoomDatabase(getDatabaseBuilder())
    }

    // datastore
    single {
        AppPreferences(dataStorePreferences())
    }

}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<CharactersDatabase>
): CharactersDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}