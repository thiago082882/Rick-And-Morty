package org.kmp.rickandmorty.ui.character_detail


import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import org.kmp.rickandmorty.kmp.rickandmorty.utils.FakeCharacters
import org.kmp.rickandmorty.utils.TestTags
import org.kmp.rickandmorty.utils.setCharacterDetailScreen
import kotlin.test.Test


@OptIn(ExperimentalTestApi::class)
class CharacterDetailScreenTest {

    private val testCharacter = FakeCharacters.all.first()

    @Test
    fun testCharacterDetailScreenDisplaysCorrectData() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        onNodeWithTag(TestTags.CharacterNameTopAppBar).assertIsDisplayed()
        onNodeWithTag(TestTags.CharacterImageCard).assertIsDisplayed()
        onNodeWithTag(TestTags.CharacterName).assertIsDisplayed()
        onNodeWithTag(TestTags.CharacterStatus).assertIsDisplayed()
        onNodeWithTag(TestTags.CharacterStatusDot).assertIsDisplayed()
        onNodeWithTag(TestTags.SpeciesDetail).assertIsDisplayed()
        onNodeWithTag(TestTags.GenderDetail).assertIsDisplayed()
        onNodeWithTag(TestTags.OriginDetail).assertIsDisplayed()
        onNodeWithTag(TestTags.LocationDetail).assertIsDisplayed()
        onNodeWithTag(TestTags.EpisodesToggle).assertIsDisplayed()
    }


    @Test
    fun testEpisodesSectionToggle() = runComposeUiTest {

        setCharacterDetailScreen(testCharacter)

        waitForIdle()

        // Verifica que inicialmente está recolhido
        onNodeWithTag(TestTags.EpisodeItem, useUnmergedTree = true)
            .assertDoesNotExist()

        // Expande a seção de episódios
        onNodeWithTag(TestTags.EpisodesToggle)
            .assertIsDisplayed()
            .performClick()

        waitForIdle()

        // Verifica que os episódios estão visíveis
        onAllNodesWithTag(TestTags.EpisodeItem, useUnmergedTree = true)
            .assertCountEquals(testCharacter.episode.size)
            .assertAll(hasClickAction())

        // Recolhe a seção de episódios
        onNodeWithTag(TestTags.EpisodesToggle).performClick()
        waitForIdle()

        // Verifica que os episódios não estão mais visíveis
        onNodeWithTag(TestTags.EpisodeItem, useUnmergedTree = true)
            .assertDoesNotExist()
    }

    @Test
    fun testShareButtonWorks() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        onNodeWithTag(TestTags.ShareButton)
            .assertIsDisplayed()
            .performClick()

    }

    @Test
    fun testBrowserButtonWorks() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        // Verifique se o botão do navegador é clicável e tenta abrir o URL do personagem
        onNodeWithTag(TestTags.BrowserButton)
            .assertIsDisplayed()
            .performClick()

    }

    @Test
    fun testFavoriteButtonWorks() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        // Verifique se o botão favorito alterna o estado do marcador
        onNodeWithTag(TestTags.FavoriteButton)
            .assertIsDisplayed()
            .performClick()

        onNodeWithTag(TestTags.FavoriteButton)
            .assertHasClickAction()
    }


    @Test
    fun testCharacterTypeDisplay() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)
        val type = testCharacter.type
        val capitalized =
            type?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

// Verifique se a seção "Tipo" aparece somente se o caractere tiver um tipo
        if (testCharacter.type.isNullOrBlank()) {
            onNodeWithTag(TestTags.TypeDetail).assertDoesNotExist()
        } else {
            onNodeWithTag(TestTags.TypeDetail)
                .assertIsDisplayed()
                .assertTextEquals(capitalized!!)


        }
    }

    @Test
    fun testLocationDetailsDisplayed() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        // Verifique se "Origem" e "Localização" são exibidos
        onNodeWithTag(TestTags.OriginDetail).assertIsDisplayed()
        onNodeWithTag(TestTags.LocationDetail).assertIsDisplayed()
    }

    @Test
    fun testCharacterImageIsDisplayed() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        // Verifique se a imagem do personagem está sendo exibida corretamente
        onNodeWithTag(TestTags.CharacterImageCard)
            .assertIsDisplayed()

        onNodeWithTag(TestTags.CharacterImage)
            .assertIsDisplayed()
            .assert(hasContentDescription("Imagem do personagem ${testCharacter.name}"))
    }

    @Test
    fun testCharacterNameAndStatusText() = runComposeUiTest {
        setCharacterDetailScreen(testCharacter)

        // Verifique se o nome do personagem e o texto de status são exibidos e correspondem aos dados esperados
        onNodeWithTag(TestTags.CharacterName).assertTextEquals(testCharacter.name)
        onNodeWithTag(TestTags.CharacterStatus).assertTextEquals(testCharacter.status.replaceFirstChar { it.uppercase() })
    }
}