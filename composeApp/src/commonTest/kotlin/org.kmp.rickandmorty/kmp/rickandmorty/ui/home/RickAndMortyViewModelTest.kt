package org.kmp.rickandmorty.kmp.rickandmorty.ui.home

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kmp.rickandmorty.kmp.rickandmorty.data.repository.FakeRickAndMortyRepository
import org.kmp.rickandmorty.ui.home.RickAndMortyViewModel
import org.kmp.rickandmorty.utils.Resource
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RickAndMortyViewModelTest {

    private lateinit var viewModel: RickAndMortyViewModel
    private lateinit var repository: FakeRickAndMortyRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeRickAndMortyRepository()
        viewModel = RickAndMortyViewModel(repository)
    }

    @Test
    fun `fetchCharactersEmitsLoadingAndThenSuccess`() = runTest {
        viewModel.charactersStateFlow.test {
            viewModel.fetchCharacters()

            assertTrue(awaitItem() is Resource.Loading)
            val success = awaitItem()
            assertTrue(success is Resource.Success)
            assertEquals(6, success.data.size)
            assertEquals("Rick Sanchez", success.data[0].name)
        }
    }

    @Test
    fun `testToggleCategoryAddsAndRemovesMultipleCategories`() {
        // Começa com a categoria vazia
        assertTrue(viewModel.selectedCategories.isEmpty())

        // Adiciona categorias
        viewModel.toggleCategory("alive")
        viewModel.toggleCategory("dead")
        viewModel.toggleCategory("unknown")

        // Verifica se as categorias foram adicionadas corretamente
        assertTrue(viewModel.selectedCategories.contains("alive"))
        assertTrue(viewModel.selectedCategories.contains("dead"))
        assertTrue(viewModel.selectedCategories.contains("unknown"))

        // Remove uma categoria e verifica
        viewModel.toggleCategory("alive")
        assertFalse(viewModel.selectedCategories.contains("alive"))

        // Remove outra categoria e verifica
        viewModel.toggleCategory("dead")
        assertFalse(viewModel.selectedCategories.contains("dead"))

        // Verifica se "unknown" ainda está presente
        assertTrue(viewModel.selectedCategories.contains("unknown"))
    }

    @Test
    fun `testToggleCategoryAddsAndRemovesCategory`() = runTest {
        assertTrue(viewModel.selectedCategories.isEmpty())

        viewModel.toggleCategory("alive")
        assertTrue(viewModel.selectedCategories.contains("alive"))

        viewModel.toggleCategory("alive")
        assertFalse(viewModel.selectedCategories.contains("alive"))
    }

    @Test
    fun `testFilterCharactersBySelectedCategories`() = runTest {

        viewModel.toggleCategory("alive")
        viewModel.toggleCategory("dead")
        viewModel.toggleCategory("unknown")

        viewModel.fetchCharacters()

        val resource = viewModel.charactersStateFlow.value

        assertTrue(resource is Resource.Success)
        val filteredCharacters = resource.data

        assertTrue(filteredCharacters.any { it.status == "Alive" })
        assertTrue(filteredCharacters.any { it.status == "Dead" })
        assertTrue(filteredCharacters.any { it.status == "Unknown" })
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
