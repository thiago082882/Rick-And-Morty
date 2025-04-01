package org.kmp.rickandmorty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RickAndMortyResponse(
    @SerialName("info") val info: Info,
    @SerialName("results") val results: List<Character>
)