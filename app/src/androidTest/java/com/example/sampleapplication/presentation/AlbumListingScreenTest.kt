package com.example.sampleapplication.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.sampleapplication.data.AlbumData
import com.example.sampleapplication.data.AlbumRepository
import com.example.sampleapplication.data.AlbumRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class AlbumListingScreenTest{

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testScreen(){
        val viewModel = AlbumViewModel(FakeRepository())

        composeTestRule.setContent {

            AlbumListingScreen(
                viewModel = viewModel
            )
        }

        composeTestRule.onNodeWithText("Album 1").isDisplayed()
    }
}

class FakeRepository(): AlbumRepository {

    override suspend fun getAlbumData(): Flow<Result<List<AlbumData>>> = flow{
       emit(Result.success(listOf(AlbumData(1,1,"Album 1"))))
    }
}