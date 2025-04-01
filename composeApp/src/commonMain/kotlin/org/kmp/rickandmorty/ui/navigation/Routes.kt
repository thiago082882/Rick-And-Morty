package org.kmp.rickandmorty.ui.navigation



object Graph {
    const val MainScreenGraph = "mainScreenGraph"
}

sealed class MainRouteScreen(var route: String) {
    object Home : MainRouteScreen("home")

}

sealed class RickAndMortyRouteScreen(var route: String) {
    object RickAndMortyDetail : RickAndMortyRouteScreen("rickAndMortyDetail")
}

sealed class SettingRouteScreen(var route: String) {
    object SettingDetail : SettingRouteScreen("settingDetail")
}

sealed class SavedRouteScreen(var route: String) {
    object SavedDetail :SavedRouteScreen("favorite")
}
