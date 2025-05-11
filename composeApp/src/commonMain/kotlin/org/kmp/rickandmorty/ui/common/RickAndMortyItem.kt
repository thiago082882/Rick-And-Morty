package org.kmp.rickandmorty.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.ui.home.components.CharacterStatus
import org.kmp.rickandmorty.ui.theme.imageSize
import org.kmp.rickandmorty.ui.theme.xSmallPadding
import org.kmp.rickandmorty.ui.theme.xxSmallPadding
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.logo

@Composable
fun RickAndMortyItem(
    character: Character,
    onClick: () -> Unit
) {
    val status = CharacterStatus.fromString(character.status)

    val cardColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        label = "Card Animation"
    )

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .testTag("characterItem_${character.id}")
            .padding(8.dp)
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(xSmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(12.dp))
                    .background(status.color.copy(alpha = 0.3f))
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(12.dp)),
                    model = character.image,
                    error = painterResource(Res.drawable.logo),
                    contentScale = ContentScale.Crop,
                    contentDescription = character.name
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(status.color.copy(alpha = 0.7f), status.color)
                            )
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = status.displayName,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(xxSmallPadding)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                character.type?.takeIf { it.isNotEmpty() }?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                Text(
                    text = character.species,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
        }
    }
}
