package com.example.sampleapplication.presentation

import com.example.sampleapplication.data.AlbumData
import com.example.sampleapplication.data.AlbumRepository
import com.example.sampleapplication.data.AlbumRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AlbumViewModelTest{

    val mockedRepository = mockkClass(AlbumRepositoryImpl::class)

    val classUnderTest = AlbumViewModel(FakeAlbumRepository())

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchData() = runTest{

        classUnderTest.fetchData()

        assertTrue(classUnderTest.uiState.loadError == "No data available")
    }
}

class FakeAlbumRepository(): AlbumRepository{

    override suspend fun getAlbumData(): Flow<Result<List<AlbumData>>>
        = flow<Result<List<AlbumData>>> {
            emit(Result.success(listOf()))

    }
}