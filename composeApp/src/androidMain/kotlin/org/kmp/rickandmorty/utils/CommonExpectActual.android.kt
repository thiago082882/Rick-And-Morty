package org.kmp.rickandmorty.utils

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kmp.rickandmorty.data.database.CharactersDatabase
import org.koin.mp.KoinPlatform
import java.util.UUID

actual fun shareLink(url: String) {
    try {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Link")
        activityProvider.invoke().startActivity(shareIntent)
    } catch (e: IllegalArgumentException) {
        Log.e("ShareLink", "Activity not set yet. Ignoring share call.", e)
    }
}

private var activityProvider : () -> Activity = {
    throw IllegalArgumentException("Error")
}

fun setActivityProvider(provider :() -> Activity){
    activityProvider = provider
}
actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Mobile
}

@Composable
actual fun getScreenSize(): Size {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val screenWidthDP = configuration.screenWidthDp.dp
    return Size(width = screenWidthDP, height = screenHeightDp)
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<CharactersDatabase> {
    val appContext = KoinPlatform.getKoin().get<Application>()
    val dbFile = appContext.getDatabasePath(DB_Name)
    return Room.databaseBuilder<CharactersDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}