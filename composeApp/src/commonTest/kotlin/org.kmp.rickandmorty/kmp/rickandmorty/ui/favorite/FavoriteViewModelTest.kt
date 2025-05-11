package org.kmp.rickandmorty.kmp.rickandmorty.ui.favorite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kmp.rickandmorty.kmp.rickandmorty.data.repository.FakeLocalCharacterRepository
import org.kmp.rickandmorty.kmp.rickandmorty.utils.FakeCharacters
import org.kmp.rickandmorty.ui.favorite.FavoriteViewModel
import org.kmp.rickandmorty.utils.Resource.Loading
import org.kmp.rickandmorty.utils.Resource.Success
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var repository: FakeLocalCharacterRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeLocalCharacterRepository()
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun `initShouldLoadCharactersAndEmitLoadingThenSuccess`() = runTest {
        // Arrange
        val testCharacters = FakeCharacters.all.take(2)
        testCharacters.forEach { repository.upsertCharacter(it) }

        // Act - ViewModel is initialized in setUp()

        // Assert
        val state = viewModel.bookmarkNewsStateFlow.first { it !is Loading }
        assertTrue(state is Success)
        assertEquals(testCharacters, state.data)
    }

    @Test
    fun `getArticlesShouldEmitLoadingThenSuccessWhenRepositoryHasData`() = runTest {
        // Arrange
        val testCharacters = listOf(
            FakeCharacters.all[2], // Beth
            FakeCharacters.all[3]  // Jerry
        )
        testCharacters.forEach { repository.upsertCharacter(it) }

        // Act
        viewModel.getArticles()

        // Assert
        val state = viewModel.bookmarkNewsStateFlow.first { it !is Loading }
        assertTrue(state is Success)
        assertEquals(testCharacters, (state as Success).data)
    }

    @Test
    fun `getArticlesShouldShowEmptyListWhenNoFavoritesExist`() = runTest {
        // Arrange - Repository starts empty

        // Act
        viewModel.getArticles()

        // Assert
        val state = viewModel.bookmarkNewsStateFlow.first { it !is Loading }
        assertTrue(state is Success)
        assertTrue(state.data.isEmpty())
    }

    @Test
    fun `shouldReflectRepositoryChangesInRealTime`() = runTest {
        // Arrange
        val initialCharacters = listOf(FakeCharacters.all[0]) // Rick
        repository.upsertCharacter(initialCharacters[0])

        // Act - Get initial state
        viewModel.getArticles()
        val initialState = viewModel.bookmarkNewsStateFlow.first { it !is Loading }

        // Add another character
        val newCharacter = FakeCharacters.all[1] // Morty
        repository.upsertCharacter(newCharacter)

        // Get updated state
        val updatedState = viewModel.bookmarkNewsStateFlow.first {
            it is Success && it.data.size == 2
        }

        // Assert
        assertTrue(initialState is Success)
        assertEquals(1, initialState.data.size)

        assertTrue(updatedState is Success)
        assertEquals(2, updatedState.data.size)
        assertTrue(updatedState.data.contains(newCharacter))
    }

    @Test
    fun `shouldHandleCharacterRemovalCorrectly`() = runTest {
        // Arrange
        val testCharacters = FakeCharacters.all.take(3) // Rick, Morty, Beth
        testCharacters.forEach { repository.upsertCharacter(it) }

        // Act - Get initial state
        viewModel.getArticles()
        val initialState = viewModel.bookmarkNewsStateFlow.first {
            it is Success && it.data.size == 3
        }

        // Remove a character
        repository.deleteCharacter(testCharacters[1]) // Remove Morty

        // Get updated state
        val updatedState = viewModel.bookmarkNewsStateFlow.first {
            it is Success && it.data.size == 2
        }

        // Assert
        assertTrue(initialState is Success)
        assertEquals(3, initialState.data.size)

        assertTrue(updatedState is Success)
        assertEquals(2, updatedState.data.size)
        assertFalse(updatedState.data.any { it.id == testCharacters[1].id })
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
