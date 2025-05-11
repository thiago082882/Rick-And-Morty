package org.kmp.rickandmorty.ui.common


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.ui.navigation.RickAndMortyRouteScreen
import org.kmp.rickandmorty.ui.theme.cardMinSize
import org.kmp.rickandmorty.ui.theme.mediumPadding


@Composable
fun RickAndMortyListScreen(
    articleList: List<Character>,
    rootNavController: NavController
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.testTag("characterList"),
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    )  {
        itemsIndexed(articleList) { _, item ->
            RickAndMortyItem(character = item, onClick = {
                val articleStr = Json.encodeToString(item)
                rootNavController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("character", articleStr)
                }
                rootNavController.navigate( RickAndMortyRouteScreen.RickAndMortyDetail.route)
            })
        }

    }
}