package com.example.sampleapplication.feature_worldbank.presentation

import com.example.sampleapplication.feature_worldbank.data.ArchiveRepository
import com.example.sampleapplication.feature_worldbank.data.ArchiveSamplerResponse
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ArchiveViewModelTest {

    private val mockedRepo = mockkClass(ArchiveRepository::class)
    private val classUnderTest = ArchiveViewModel(mockedRepo)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchData() = runTest{

        coEvery { mockedRepo.fetchData() }
            .returns( flow {
                emit(listOf(ArchiveSamplerResponse.File(name = "ABC")))
            })

        classUnderTest.fetchData()

        advanceUntilIdle()

        assertTrue(classUnderTest.uiState.listOfFiles.isNotEmpty())

    }
}

