package org.kmp.rickandmorty.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.data.model.RickAndMortyResponse

 open class RickAndMortyRepository(
    private val networkModule: HttpClient
) {
     open suspend fun getAllCharacters(): List<Character> {
        val allCharacters = mutableListOf<Character>()
        var page = 1
        var hasMorePages = true

        while (hasMorePages) {
            val response: RickAndMortyResponse = networkModule.get {
                url("character?page=$page")
            }.body()

            allCharacters.addAll(response.results)
            hasMorePages = response.info.next != null
            page++
        }
        return allCharacters
    }
}

