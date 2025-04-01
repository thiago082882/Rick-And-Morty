package org.kmp.rickandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform