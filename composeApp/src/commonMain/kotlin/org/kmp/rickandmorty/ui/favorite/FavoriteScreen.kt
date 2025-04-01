package org.kmp.rickandmorty.ui.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.kmp.rickandmorty.ui.common.RickAndMortyListScreen
import org.kmp.rickandmorty.ui.common.EmptyContent
import org.kmp.rickandmorty.ui.common.ShimmerEffect
import org.kmp.rickandmorty.ui.navigation.SettingRouteScreen
import org.koin.compose.viewmodel.koinViewModel
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.ic_browse
import rickandmorty.composeapp.generated.resources.no_favorites



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    rootNavController: NavController,
    paddingValues: PaddingValues
) {
    val bookmarkViewModel = koinViewModel<FavoriteViewModel>()

    val uiState by bookmarkViewModel.bookmarkNewsStateFlow.collectAsState()
    val originDirection = LocalLayoutDirection.current
    Column(
        modifier = Modifier.fillMaxSize().padding(
            start = paddingValues.calculateStartPadding(originDirection),
            end = paddingValues.calculateEndPadding(originDirection),
            bottom = paddingValues.calculateBottomPadding(),
        ),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            navigationIcon = {
                IconButton(onClick = { rootNavController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    rootNavController.navigate(SettingRouteScreen.SettingDetail.route)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    )
                }
            }
        )

        uiState.DisplayResult(onLoading = {
            ShimmerEffect()
        }, onSuccess = { articleList ->
            if (articleList.isEmpty()) {
                EmptyContent(
                    message = stringResource(Res.string.no_favorites),
                    icon = Res.drawable.ic_browse, isOnRetryBtnVisible = false
                )
            } else {
                RickAndMortyListScreen(
                    articleList = articleList,
                    rootNavController = rootNavController
                )
            }
        }, onError = {
            EmptyContent(message = it, icon = Res.drawable.ic_browse, onRetryClick = {
                bookmarkViewModel.getArticles()
            })
        })
    }

}