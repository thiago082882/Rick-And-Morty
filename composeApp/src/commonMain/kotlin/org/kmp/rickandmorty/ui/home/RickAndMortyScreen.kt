package org.kmp.rickandmorty.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.kmp.rickandmorty.ui.common.RickAndMortyListScreen
import org.kmp.rickandmorty.ui.common.EmptyContent
import org.kmp.rickandmorty.ui.common.ShimmerEffect
import org.kmp.rickandmorty.ui.navigation.SavedRouteScreen
import org.kmp.rickandmorty.ui.navigation.SettingRouteScreen
import org.kmp.rickandmorty.ui.theme.xSmallPadding
import org.kmp.rickandmorty.utils.categoryList
import org.koin.compose.viewmodel.koinViewModel
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.ic_browse
import rickandmorty.composeapp.generated.resources.ic_network_error
import rickandmorty.composeapp.generated.resources.no_character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickAndMortyScreen(rootNavController: NavController, paddingValues: PaddingValues) {
    val viewModel = koinViewModel<RickAndMortyViewModel>()
    val uiState by viewModel.charactersStateFlow.collectAsState()
    val originDirection = LocalLayoutDirection.current

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = paddingValues.calculateStartPadding(originDirection),
                end = paddingValues.calculateEndPadding(originDirection),
                bottom = paddingValues.calculateBottomPadding(),
            )
    ) {
        TopAppBar(
            title = {
                Text(
                    modifier = Modifier
                        .testTag("appBarTitle"),
                    text = "Rick and Morty",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            actions = {
                // Bookmark icon - added first so it appears to the left of settings
                IconButton(onClick = {
                    rootNavController.navigate(SavedRouteScreen.SavedDetail.route)
                }, modifier = Modifier.testTag("bookmarkButton")
                ) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Saved items")
                }
                // Settings icon
                IconButton(onClick = {
                    rootNavController.navigate(SettingRouteScreen.SettingDetail.route)
                }, modifier = Modifier.testTag("settingsButton")) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                }
            }
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = xSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(
                xSmallPadding,
                Alignment.CenterHorizontally
            )
        ) {
            items(categoryList, key = { it }) { category ->
                FilterChip(
                    modifier = Modifier.testTag("categoryChip_$category"),
                    selected = category in viewModel.selectedCategories,
                    onClick = { viewModel.toggleCategory(category) },
                    label = { Text(category) },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = if (category in viewModel.selectedCategories)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        else MaterialTheme.colorScheme.surface,
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                    )
                )
            }
        }

        uiState.DisplayResult(
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = { characterList ->
                if (characterList.isEmpty()) {
                    EmptyContent(
                        message = stringResource(Res.string.no_character),
                        icon = Res.drawable.ic_browse,
                        onRetryClick = {
                            viewModel.fetchCharacters()
                        }
                    )
                } else {
                    RickAndMortyListScreen(
                        articleList = characterList,
                        rootNavController = rootNavController
                    )

                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_network_error,
                    onRetryClick = {
                        viewModel.fetchCharacters()
                    }
                )
            }
        )
    }
}
