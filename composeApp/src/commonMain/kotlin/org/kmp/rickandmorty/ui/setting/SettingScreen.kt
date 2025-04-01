package org.kmp.rickandmorty.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kmp.rickandmorty.ui.setting.component.BookmarkDialog
import org.kmp.rickandmorty.ui.setting.component.SettingItem
import org.kmp.rickandmorty.ui.setting.component.ThemeSelectionDialog
import org.kmp.rickandmorty.utils.Theme
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.delete_bookmark
import rickandmorty.composeapp.generated.resources.go_back
import rickandmorty.composeapp.generated.resources.ic_delete
import rickandmorty.composeapp.generated.resources.ic_light_mode
import rickandmorty.composeapp.generated.resources.setting
import rickandmorty.composeapp.generated.resources.theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel) {
    var showThemeSelectionDialog by remember { mutableStateOf(false) }
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    var showDeleteBookmarkDialog by remember { mutableStateOf(false) }

    if (showThemeSelectionDialog) {
        ThemeSelectionDialog(
            currentTheme = currentTheme ?: Theme.DARK_MODE.name,
            onThemeChange = { theme ->
                settingViewModel.changeThemeMode(theme.name)
                showThemeSelectionDialog = false
            },
            onDismissRequest = { showThemeSelectionDialog = false }
        )
    }

    if (showDeleteBookmarkDialog) {
        BookmarkDialog(
            onDeleteHistory = {
                settingViewModel.deleteHistory()
                showDeleteBookmarkDialog = false
            },
            onDismissRequest = { showDeleteBookmarkDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.setting),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Preferences",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }


            item {
                SettingItem(
                    icon = painterResource(Res.drawable.ic_light_mode),
                    title = stringResource(Res.string.theme),
                    subtitle = when (currentTheme) {
                        Theme.LIGHT_MODE.name -> "Light theme"
                        Theme.DARK_MODE.name -> "Dark theme"
                        Theme.BLUE_MODE.name -> "Blue theme"
                        Theme.GREEN_MODE.name -> "Green theme"
                        Theme.RED_MODE.name -> "Red theme"
                        else -> "Default system"
                    },
                    onClick = { showThemeSelectionDialog = true }
                )
            }

            item {
                Text(
                    text = "Maintenance",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                SettingItem(
                    icon = painterResource(Res.drawable.ic_delete),
                    title = stringResource(Res.string.delete_bookmark),
                    subtitle = "Remove all favorites",
                    iconTint = MaterialTheme.colorScheme.error,
                    textColor = MaterialTheme.colorScheme.error,
                    onClick = { showDeleteBookmarkDialog = true }
                )
            }
        }
    }
}

