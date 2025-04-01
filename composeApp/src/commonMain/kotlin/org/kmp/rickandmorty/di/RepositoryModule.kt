package org.kmp.rickandmorty.di


import org.kmp.rickandmorty.data.database.CharactersDatabase
import org.kmp.rickandmorty.data.repository.LocalCharacterRepository
import org.kmp.rickandmorty.data.repository.RickAndMortyRepository
import org.koin.dsl.module


val repositoryModule = module {

    single {
        LocalCharacterRepository(get<CharactersDatabase>().characterDao())
    }
    single {
        RickAndMortyRepository(get())
    }
}