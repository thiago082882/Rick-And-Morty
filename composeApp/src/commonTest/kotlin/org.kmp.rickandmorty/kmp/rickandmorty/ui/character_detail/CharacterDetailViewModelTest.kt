package org.kmp.rickandmorty.kmp.rickandmorty.ui.character_detail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.kmp.rickandmorty.kmp.rickandmorty.data.repository.FakeLocalCharacterRepository
import org.kmp.rickandmorty.kmp.rickandmorty.utils.FakeCharacters
import org.kmp.rickandmorty.ui.character_detail.CharacterDetailViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {
    private lateinit var viewModel: CharacterDetailViewModel
    private lateinit var fakeRepository: FakeLocalCharacterRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    private val sampleCharacter get() = FakeCharacters.all.first()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeLocalCharacterRepository()
        viewModel = CharacterDetailViewModel(fakeRepository, testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `givenBookmarkedCharacter_whenCheckingIfBookmarked_thenShouldReturnTrue`() =
        runTest {
            fakeRepository.upsertCharacter(sampleCharacter)

            viewModel.isArticleBookmark(sampleCharacter)
            advanceUntilIdle()

            assertTrue(viewModel.isBookmarked)
        }

    @Test
    fun `givenCharacter_whenBookmarkingIt_thenShouldSaveAndMarkAsBookmarked`() =
        runTest {
            viewModel.bookmarkArticle(sampleCharacter)
            advanceUntilIdle()

            assertTrue(viewModel.isBookmarked)
            val saved = fakeRepository.getCharacter(sampleCharacter.id)
            assertEquals(sampleCharacter, saved)
        }
}
