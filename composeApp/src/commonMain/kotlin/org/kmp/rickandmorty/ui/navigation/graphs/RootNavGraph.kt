package org.kmp.rickandmorty.ui.navigation.graphs


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.json.Json
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.ui.favorite.BookmarkScreen
import org.kmp.rickandmorty.ui.character_detail.CharacterDetailScreen
import org.kmp.rickandmorty.ui.navigation.Graph
import org.kmp.rickandmorty.ui.navigation.RickAndMortyRouteScreen
import org.kmp.rickandmorty.ui.navigation.SavedRouteScreen
import org.kmp.rickandmorty.ui.navigation.SettingRouteScreen
import org.kmp.rickandmorty.ui.setting.SettingScreen
import org.kmp.rickandmorty.ui.setting.SettingViewModel


@Composable
fun RootNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues,
    settingViewModel: SettingViewModel
) {
    NavHost(
        navController = rootNavController,
        startDestination = Graph.MainScreenGraph,
    ) {
        mainNavGraph(rootNavController = rootNavController, innerPadding = innerPadding)
        composable(
            route = RickAndMortyRouteScreen.RickAndMortyDetail.route,
        ){
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("character")
                ?.let { character ->
                    val currentCharacter: Character = Json.decodeFromString(character)
                    CharacterDetailScreen(rootNavController, currentCharacter)
                }
        }
        composable(
            route = SettingRouteScreen.SettingDetail.route,
        ) {
            SettingScreen(navController = rootNavController, settingViewModel)
        }

        composable(route = SavedRouteScreen.SavedDetail.route) {
            BookmarkScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }

    }
}