package org.kmp.rickandmorty.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.kmp.rickandmorty.data.model.Origin
import org.kmp.rickandmorty.data.model.Location
import org.kmp.rickandmorty.data.model.Source

class CharacterTypeConverters {

    // Converters para o tipo Source
    @TypeConverter
    fun fromSourceToString(value: Source): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSource(value: String): Source {
        return Json.decodeFromString(value)
    }

    // Converters para o tipo Origin
    @TypeConverter
    fun fromOriginToString(value: Origin): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToOrigin(value: String): Origin {
        return Json.decodeFromString(value)
    }

    // Converters para o tipo Location
    @TypeConverter
    fun fromLocationToString(value: Location): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToLocation(value: String): Location {
        return Json.decodeFromString(value)
    }

    // Converters para o tipo List<String>
    @TypeConverter
    fun fromListToString(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        return Json.decodeFromString(value)
    }
}
