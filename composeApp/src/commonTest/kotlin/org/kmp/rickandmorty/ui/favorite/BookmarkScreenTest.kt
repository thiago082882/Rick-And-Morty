package org.kmp.rickandmorty.ui.favorite



import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.navigation.compose.rememberNavController
import org.kmp.rickandmorty.kmp.rickandmorty.utils.FakeCharacters
import org.kmp.rickandmorty.ui.common.RickAndMortyListScreen
import org.kmp.rickandmorty.ui.common.EmptyContent
import kotlin.test.Test
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.ic_browse


class BookmarkScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkTopAppBarTitleIsVisible() = runComposeUiTest {
        setContent {
            BookmarkScreen(
                rootNavController = rememberNavController(),
                paddingValues = PaddingValues()
            )
        }

        onNodeWithTag("appBarTitle").assertIsDisplayed()
        onNodeWithTag("appBarTitle").assertTextEquals("Favorites")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkNavigationButtonsExist() = runComposeUiTest {
        setContent {
            BookmarkScreen(
                rootNavController = rememberNavController(),
                paddingValues = PaddingValues()
            )
        }

        onNodeWithTag("backButton").assertIsDisplayed()
        onNodeWithTag("settingsButton").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkFavoriteListIsDisplayedWithFakeData() = runComposeUiTest {
        val favorites = FakeCharacters.all.filter { it.id == "1" || it.id == "2" }

        setContent {
            RickAndMortyListScreen(
                articleList = favorites,
                rootNavController = rememberNavController()
            )
        }

        onNodeWithTag("characterItem_1").assertIsDisplayed()
        onNodeWithTag("characterItem_2").assertIsDisplayed()
        onNodeWithTag("characterList").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkEmptyStateIsDisplayedWhenNoFavorites() = runComposeUiTest {
        setContent {
            EmptyContent(
                message = "No favorites",
                icon = Res.drawable.ic_browse,
                isOnRetryBtnVisible = false
            )
        }

        // Verifica se o container principal está visível
        onNodeWithTag("emptyContent").assertIsDisplayed()

        // Verifica se a mensagem está correta
        onNodeWithTag("emptyMessage")
            .assertIsDisplayed()
            .assertTextEquals("No favorites")

        // Verifica se o ícone está visível
        onNodeWithTag("emptyIcon").assertIsDisplayed()

        // Verifica que o botão de retry não está visível
        onNodeWithTag("retryButton").assertDoesNotExist()
    }
}