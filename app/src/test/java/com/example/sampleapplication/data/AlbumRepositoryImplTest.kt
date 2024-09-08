package com.example.sampleapplication.data

import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response

class AlbumRepositoryImplTest{

    private val mockedDataSource: AlbumDataSource = mockkClass(AlbumDataSource::class)

    private val classUnderTest = AlbumRepositoryImpl(mockedDataSource)


    @Test
    fun testGetAlbumData() = runBlocking{

        coEvery {
            mockedDataSource.getAlbumList()
        }.returns(Response.success(listOf()))

        val emittedResult = classUnderTest.getAlbumData().single()

        assertTrue(emittedResult.isSuccess)
    }

    @Test
    fun testGetAlbumData_nullResponse() = runBlocking{

        coEvery {
            mockedDataSource.getAlbumList()
        }.returns(Response.success(null))

        val emittedResult = classUnderTest.getAlbumData().single()

        assertTrue(emittedResult.isFailure)
    }

    @Test
    fun testGetAlbumData_failure() = runTest {


        coEvery {
            mockedDataSource.getAlbumList()
        }.returns(Response.error( 403, ResponseBody.create(MediaType.parse("text/plain"), "No data")))

        val emittedResult = classUnderTest.getAlbumData().single()

        assertTrue(emittedResult.isFailure)

    }
}