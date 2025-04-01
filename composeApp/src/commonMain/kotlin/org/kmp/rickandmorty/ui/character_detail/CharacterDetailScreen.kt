package org.kmp.rickandmorty.ui.character_detail


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.ui.character_detail.components.CharacterDetailItem
import org.kmp.rickandmorty.ui.character_detail.components.EpisodeItem
import org.kmp.rickandmorty.ui.home.components.CharacterStatus
import org.kmp.rickandmorty.ui.theme.mediumPadding
import org.kmp.rickandmorty.ui.theme.xLargePadding
import org.kmp.rickandmorty.utils.shareLink
import org.koin.compose.viewmodel.koinViewModel
import rickandmorty.composeapp.generated.resources.Res

import rickandmorty.composeapp.generated.resources.ic_browse
import rickandmorty.composeapp.generated.resources.ic_favorite
import rickandmorty.composeapp.generated.resources.ic_favorite_border
import rickandmorty.composeapp.generated.resources.logo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    navController: NavController,
    currentCharacter: Character
) {
    val rickAndMortyViewModel = koinViewModel<CharacterDetailViewModel>()
    LaunchedEffect(Unit) {
        rickAndMortyViewModel.isArticleBookmark(currentCharacter)
    }
    val url = LocalUriHandler.current
    var expandedEpisodes by remember { mutableStateOf(false) }
    val status = CharacterStatus.fromString(currentCharacter.status)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                title = {
                    Text(
                        text = currentCharacter.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(
                        onClick = { shareLink(currentCharacter.url) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Compartilhar",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(
                        onClick = { url.openUri(currentCharacter.url) }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_browse),
                            contentDescription = "Abrir no navegador",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    IconButton(
                        onClick = { rickAndMortyViewModel.bookmarkArticle(currentCharacter) }
                    ) {
                        Icon(
                            painter = painterResource(
                                if (rickAndMortyViewModel.isBookmarked) Res.drawable.ic_favorite
                                else Res.drawable.ic_favorite_border
                            ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer
//                )
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = xLargePadding, vertical = mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(xLargePadding)
        ) {
            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    shape = MaterialTheme.shapes.large,
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.surfaceVariant),
                        model = currentCharacter.image,
                        error = painterResource(Res.drawable.logo),
                        contentDescription = "Imagem do personagem ${currentCharacter.name}",
                        contentScale = ContentScale.Crop
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(mediumPadding)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(mediumPadding)
                    ) {
                        Text(
                            text = currentCharacter.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(
                                        status.color
                                    )
                            )
                            Text(
                                text = currentCharacter.status.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }


                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            CharacterDetailItem(
                                title = "Species",
                                value = currentCharacter.species.replaceFirstChar { it.uppercase() }
                            )

                            currentCharacter.type?.takeIf { it.isNotBlank() }?.let { type ->
                                CharacterDetailItem(
                                    title = "Type",
                                    value = type.replaceFirstChar { it.uppercase() }
                                )
                            }

                            CharacterDetailItem(
                                title = "Gender",
                                value = currentCharacter.gender.replaceFirstChar { it.uppercase() }
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Location",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )

                            CharacterDetailItem(
                                title = "Origin",
                                value = currentCharacter.origin.name.ifEmpty { "Unknown" }
                            )

                            CharacterDetailItem(
                                title = "Last known",
                                value = currentCharacter.location.name.ifEmpty { "Unknown" }
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                        )
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedEpisodes = !expandedEpisodes }
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Episodes (${currentCharacter.episode.size})",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Icon(

                                    imageVector = if (expandedEpisodes) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            if (expandedEpisodes) {
                                Divider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                                )

                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    currentCharacter.episode.forEachIndexed { index, episodeUrl ->
                                        EpisodeItem(
                                            episodeNumber = index + 1,
                                            url = episodeUrl,
                                            modifier = Modifier.clickable {
                                                url.openUri(episodeUrl)
                                            }

                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

