package org.kmp.rickandmorty.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kmp.rickandmorty.data.database.CharactersDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.UUID

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Desktop
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSize(): Size {
    val configuration = LocalWindowInfo.current
    val screenHeightDp = configuration.containerSize.height.dp
    val screenWidthDP = configuration.containerSize.width.dp
    return Size(width = screenWidthDP, height = screenHeightDp)
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<CharactersDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<CharactersDatabase>(
        name = dbFile.absolutePath,
    )
}