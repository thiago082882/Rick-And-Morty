package org.kmp.rickandmorty.ui.home


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.navigation.compose.rememberNavController
import org.kmp.rickandmorty.kmp.rickandmorty.utils.FakeCharacters
import org.kmp.rickandmorty.ui.common.RickAndMortyListScreen
import kotlin.test.Test

class RickAndMortyScreenTest {


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkTopAppBarTitleIsVisible() = runComposeUiTest {
        setContent {
            RickAndMortyScreen(
                rootNavController = rememberNavController(),
                paddingValues = PaddingValues()
            )
        }

        onNodeWithTag("appBarTitle").assertIsDisplayed()
        onNodeWithTag("appBarTitle").assertTextEquals("Rick and Morty")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkActionButtonsExist() = runComposeUiTest {
        setContent {
            RickAndMortyScreen(
                rootNavController = rememberNavController(),
                paddingValues = PaddingValues()
            )
        }

        onNodeWithTag("bookmarkButton").assertIsDisplayed()
        onNodeWithTag("settingsButton").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkAllFilterChipsAreVisible() = runComposeUiTest {
        setContent {
            RickAndMortyScreen(
                rootNavController = rememberNavController(),
                paddingValues = PaddingValues()
            )
        }

        for (category in org.kmp.rickandmorty.utils.categoryList) {
            onNodeWithTag("categoryChip_$category").assertIsDisplayed()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkCharacterListIsDisplayedWithFakeData() = runComposeUiTest {
        setContent {
            RickAndMortyListScreen(
                articleList = FakeCharacters.all,
                rootNavController = rememberNavController()
            )
        }


        onNodeWithTag("characterItem_1").assertIsDisplayed()
        onNodeWithTag("characterItem_2").assertIsDisplayed()


        onNodeWithTag("characterList").assertIsDisplayed()
    }

}
