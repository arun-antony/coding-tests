package com.example.sampleapplication.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class AlbumDataSourceTest{

    private val mockedApi = mockkClass(AlbumApi::class)

    private val classUnderTest = AlbumDataSource(mockedApi)



    @Test
    fun testGetAlbumList() = runBlocking{

        coEvery {
            mockedApi.getAlbums()
        }.returns(Response.success(emptyList()))

        classUnderTest.getAlbumList()

        coVerify {
            mockedApi.getAlbums()
        }

    }
}