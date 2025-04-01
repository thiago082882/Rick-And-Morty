package org.kmp.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "characterTable")
data class Character(
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("type")
    val type: String?,
    @SerialName("gender")
    val gender: String,
    @SerialName("origin")
    val origin: Origin,
    @SerialName("location")
    val location: Location,
    @SerialName("image")
    val image: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("created")
    val created: String
)