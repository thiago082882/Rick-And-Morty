package org.kmp.rickandmorty.di

import org.koin.dsl.module
import org.kmp.rickandmorty.ui.home.RickAndMortyViewModel
import  org.kmp.rickandmorty.ui.character_detail.CharacterDetailViewModel
import org.kmp.rickandmorty.ui.setting.SettingViewModel
import org.kmp.rickandmorty.ui.favorite.FavoriteViewModel

import org.koin.core.module.dsl.viewModelOf


val viewmodelModule = module {

    viewModelOf(::RickAndMortyViewModel)

    viewModelOf(::CharacterDetailViewModel)

    viewModelOf(::SettingViewModel)

    viewModelOf(::FavoriteViewModel)




}