package org.kmp.rickandmorty.utils

import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.room.Room
import org.kmp.rickandmorty.data.database.CharactersDatabase
import org.kmp.rickandmorty.data.database.instantiateImpl
import platform.UIKit.*
import platform.Foundation.NSUUID
import platform.Foundation.NSHomeDirectory

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

actual fun getType(): Type {
    return Type.Mobile
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
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"
    return Room.databaseBuilder<CharactersDatabase>(
        name = dbFilePath,
        factory =  { CharactersDatabase::class.instantiateImpl() }
    )
}
