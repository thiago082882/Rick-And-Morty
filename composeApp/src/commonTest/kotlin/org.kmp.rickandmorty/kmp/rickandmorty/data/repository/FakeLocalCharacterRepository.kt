package org.kmp.rickandmorty.kmp.rickandmorty.data.repository

import org.kmp.rickandmorty.data.repository.LocalCharacterRepository

class FakeLocalCharacterRepository : LocalCharacterRepository(FakeCharacterDao())
