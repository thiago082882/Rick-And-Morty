package org.kmp.rickandmorty.ui.common


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.kmp.rickandmorty.ui.theme.imageSize
import org.kmp.rickandmorty.ui.theme.smallPadding
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.retry


@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    message: String,
    icon: DrawableResource,
    isOnRetryBtnVisible: Boolean = true,
    onRetryClick: () -> Unit = { }
) {
    Column(
        modifier = modifier.fillMaxSize()
            .testTag("emptyContent"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(imageSize)
                .testTag("emptyIcon"),
            painter = painterResource(icon),
            tint = if (!isSystemInDarkTheme()) LightGray else DarkGray,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(smallPadding)
                .testTag("emptyMessage"),
            text = message,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = if (!isSystemInDarkTheme()) LightGray else DarkGray,
        )
        if (isOnRetryBtnVisible) {
            Button(onClick = onRetryClick,modifier = Modifier.testTag("retryButton")) {
                Text(
                    text = stringResource(Res.string.retry),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
