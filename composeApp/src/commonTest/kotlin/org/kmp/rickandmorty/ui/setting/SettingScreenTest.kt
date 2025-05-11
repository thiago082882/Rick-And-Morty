package org.kmp.rickandmorty.ui.setting


import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.navigation.compose.rememberNavController
import org.kmp.rickandmorty.ui.setting.component.BookmarkDialog
import org.kmp.rickandmorty.ui.setting.component.ThemeSelectionDialog
import org.kmp.rickandmorty.utils.Theme
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class SettingScreenTest {
    private lateinit var viewModel: SettingViewModel
    private lateinit var fakePrefs: FakeAppPreferences
    private lateinit var fakeRepo: FakeLocalCharacterRepository

    @BeforeTest
    fun setup() {
        fakePrefs = FakeAppPreferences().apply {
            currentTheme = Theme.LIGHT_MODE.name
        }
        fakeRepo = FakeLocalCharacterRepository()
        viewModel = SettingViewModel(fakePrefs, fakeRepo)
    }

    @Test
    fun testSettingScreen_isDisplayed() = runComposeUiTest {
        setContent {
            SettingScreen(
                navController = rememberNavController(),
                settingViewModel = viewModel
            )
        }

        waitForIdle()

        // Substitua verificações de texto por verificações de tag
        onNodeWithTag("SettingScreenTopBar", useUnmergedTree = true)
            .assertIsDisplayed()

        // Verifique itens pelas tags apenas
        onNodeWithTag("SettingItem-Theme", useUnmergedTree = true)
            .assertIsDisplayed()

        onNodeWithTag("SettingItem-DeleteBookmark", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun testThemeSelectionDialog_worksAsExpected() = runComposeUiTest {
        var selectedTheme: Theme? = null

        setContent {
            ThemeSelectionDialog(
                currentTheme = Theme.LIGHT_MODE.name,
                onDismissRequest = {},
                onThemeChange = { selectedTheme = it }
            )
        }

        waitUntil(timeoutMillis = 3000) {
            onAllNodesWithTag("ThemeDialogTitle", useUnmergedTree = true).fetchSemanticsNodes()
                .isNotEmpty()
        }


        onNodeWithTag("ThemeDialogTitle", useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals("Choose a theme")


        onNodeWithTag("Radio_DARK_MODE", useUnmergedTree = true)
            .performClick()


        onNodeWithTag("ApplyButton", useUnmergedTree = true)
            .performClick()


        assertEquals(selectedTheme, Theme.DARK_MODE)
    }


    @Test
    fun testClickOnDeleteBookmark_opensAndConfirmsDeleteDialog() = runComposeUiTest {
        var wasDeleted = false

        setContent {
            BookmarkDialog(
                onDeleteHistory = { wasDeleted = true },
                onDismissRequest = { },
            )
        }


        onNodeWithTag("DeleteDialogTitle", useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals("Delete favorite")

        onNodeWithTag("DeleteDialogMessage", useUnmergedTree = true)
            .assertIsDisplayed()


        onNodeWithTag("ConfirmDeleteButton", useUnmergedTree = true)
            .performClick()


        assertTrue(wasDeleted)
    }


    @Test
    fun testPreferencesTitle_isDisplayed() = runComposeUiTest {
        setContent {
            SettingScreen(
                navController = rememberNavController(),
                settingViewModel = viewModel
            )
        }

        waitForIdle()

        onNodeWithTag("PreferencesTitle", useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals("Preferences")
    }

    @Test
    fun testOpenDialog_WhenClickingOnDeleteBookmark() = runComposeUiTest {
        setContent {
            SettingScreen(
                navController = rememberNavController(),
                settingViewModel = viewModel
            )
        }

        waitForIdle()

        onNodeWithTag("SettingItem-DeleteBookmark", useUnmergedTree = true)
            .performClick()

        waitForIdle()

        onNodeWithTag("BookmarkDialog", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun testSelectThemeRadioButton() = runComposeUiTest {
        setContent {
            ThemeSelectionDialog(
                currentTheme = "DARK_MODE",
                onThemeChange = {},
                onDismissRequest = {}
            )
        }

        waitForIdle()

        onNodeWithTag("Radio_RED_MODE", useUnmergedTree = true)
            .performClick()

        waitForIdle()

        onNodeWithTag("Radio_RED_MODE", useUnmergedTree = true)
            .assertIsSelected()
    }


    @Test
    fun testBackButton_navigatesBack() = runComposeUiTest {
        setContent {
            SettingScreen(
                navController = rememberNavController(),
                settingViewModel = viewModel
            )
        }

        waitForIdle()

        onNodeWithTag("BackButton", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()
    }

}