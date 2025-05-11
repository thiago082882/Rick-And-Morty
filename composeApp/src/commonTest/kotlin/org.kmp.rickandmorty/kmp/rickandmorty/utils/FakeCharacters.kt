package org.kmp.rickandmorty.kmp.rickandmorty.utils


import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.data.model.Location
import org.kmp.rickandmorty.data.model.Origin

object FakeCharacters {
    val all = listOf(
        Character(
            id = "1",
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = Origin(name = "Earth (C-137)", url = ""),
            location = Location(name = "Citadel of Ricks", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        ),
        Character(
            id = "2",
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = Origin(name = "Earth (C-137)", url = ""),
            location = Location(name = "Citadel of Ricks", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/2",
            created = "2017-11-04T18:50:21.651Z"
        ),
        Character(
            id = "3",
            name = "Beth Smith",
            status = "Dead",
            species = "Human",
            type = "",
            gender = "Female",
            origin = Origin(name = "Earth (C-137)", url = ""),
            location = Location(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/2"),
            url = "https://rickandmortyapi.com/api/character/3",
            created = "2017-11-04T18:52:13.890Z"
        ),
        Character(
            id = "4",
            name = "Jerry Smith",
            status = "Dead",
            species = "Human",
            type = "",
            gender = "Male",
            origin = Origin(name = "Earth (C-137)", url = ""),
            location = Location(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/3"),
            url = "https://rickandmortyapi.com/api/character/4",
            created = "2017-11-04T18:54:02.539Z"
        ),
        Character(
            id = "5",
            name = "Summer Smith",
            status = "Unknown",
            species = "Human",
            type = "",
            gender = "Female",
            origin = Origin(name = "Earth (C-137)", url = ""),
            location = Location(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/4"),
            url = "https://rickandmortyapi.com/api/character/5",
            created = "2017-11-04T18:55:37.124Z"
        ),
        Character(
            id = "6",
            name = "Evil Morty",
            status = "Unknown",
            species = "Human",
            type = "",
            gender = "Male",
            origin = Origin(name = "Earth (C-137)", url = ""),
            location = Location(name = "Citadel of Ricks", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/5"),
            url = "https://rickandmortyapi.com/api/character/6",
            created = "2017-11-04T18:57:21.873Z"
        )
    )
}
