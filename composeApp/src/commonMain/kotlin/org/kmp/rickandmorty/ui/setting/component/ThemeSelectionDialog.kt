package org.kmp.rickandmorty.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.jetbrains.compose.resources.stringResource
import org.kmp.rickandmorty.ui.theme.mediumPadding
import org.kmp.rickandmorty.ui.theme.xLargePadding
import org.kmp.rickandmorty.ui.theme.xSmallPadding
import org.kmp.rickandmorty.utils.Theme
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.apply
import rickandmorty.composeapp.generated.resources.cancel
import rickandmorty.composeapp.generated.resources.choose_a_theme
import rickandmorty.composeapp.generated.resources.delete
import rickandmorty.composeapp.generated.resources.delete_bookmark
import rickandmorty.composeapp.generated.resources.delete_bookmark_description

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    onThemeChange: (Theme) -> Unit, onDismissRequest: () -> Unit, currentTheme: String,modifier: Modifier =Modifier
) {

    var currentSelectedTheme by remember { mutableStateOf(Theme.valueOf(currentTheme)) }

    BasicAlertDialog(onDismissRequest = onDismissRequest, content = {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .testTag("ThemeDialogContent"),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(mediumPadding)) {

                Text(
                    text = stringResource(Res.string.choose_a_theme),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(xSmallPadding)
                        .testTag("ThemeDialogTitle")
                )
                Theme.entries.forEach { theme ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable {
                            currentSelectedTheme = theme
                        }
                            .testTag("ThemeOption_${theme.name}"),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = currentSelectedTheme == theme,
                            onClick = { currentSelectedTheme = theme },
                            modifier = Modifier.testTag("Radio_${theme.name}"),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(text = stringResource(theme.title),
                            modifier = Modifier.testTag("ThemeText_${theme.name}"))
                    }
                }

                Spacer(modifier = Modifier.height(xLargePadding))

                Row(
                    modifier = Modifier.fillMaxWidth().testTag("ThemeDialogButtons"), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest, modifier = Modifier.testTag("CancelButton") ) {
                        Text(text = stringResource(Res.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(mediumPadding))
                    TextButton(onClick = { onThemeChange(currentSelectedTheme) },
                        modifier = Modifier.testTag("ApplyButton") ) {
                        Text(text = stringResource(Res.string.apply))
                    }
                }
            }
        }
    })
}


@Composable
fun BookmarkDialog(
    onDeleteHistory: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = {
            Text(
                stringResource(Res.string.delete_bookmark),
                modifier = Modifier.testTag("DeleteDialogTitle")
            )
        },
        text = {
            Text(
                stringResource(Res.string.delete_bookmark_description),
                modifier = Modifier.testTag("DeleteDialogMessage")
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(Res.string.delete_bookmark),
                modifier = Modifier.testTag("DeleteDialogIcon")
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onDeleteHistory() },
                modifier = Modifier.testTag("ConfirmDeleteButton")
            ) {
                Text(stringResource(Res.string.delete))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                modifier = Modifier.testTag("CancelDeleteButton")
            ) {
                Text(stringResource(Res.string.cancel))
            }
        }
    )
}