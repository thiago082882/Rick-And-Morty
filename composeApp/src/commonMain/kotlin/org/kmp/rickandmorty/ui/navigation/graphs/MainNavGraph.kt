package org.kmp.rickandmorty.ui.navigation.graphs


import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.kmp.rickandmorty.ui.home.RickAndMortyScreen
import org.kmp.rickandmorty.ui.navigation.Graph
import org.kmp.rickandmorty.ui.navigation.MainRouteScreen


fun NavGraphBuilder.mainNavGraph(
    rootNavController: NavHostController,
    innerPadding: PaddingValues
) {
    navigation(
        startDestination = MainRouteScreen.Home.route,
        route = Graph.MainScreenGraph
    ) {
        composable(route = MainRouteScreen.Home.route) {
            RickAndMortyScreen(rootNavController = rootNavController, paddingValues = innerPadding)
        }



    }

}