package com.example.sampleapplication.feature_worldbank.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.sampleapplication.commons.network.NetworkState
import org.junit.Rule
import org.junit.Test

class ArchiveListScreenKtTest{

    @get: Rule val composeTestRule = createComposeRule()

    @Test
    fun testScreen(){
        val viewModel = ArchiveViewModel()

        composeTestRule.setContent {
            ArchiveListScreen(viewModel = viewModel, networkState = NetworkState.Connected, showSnackBar = {})
        }

        composeTestRule.onNodeWithText("ABC").isDisplayed()
        composeTestRule.onNodeWithText("format2").isDisplayed()
    }
}
