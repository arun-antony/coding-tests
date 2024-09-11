package com.example.sampleapplication.feature_worldbank.data

import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ArchiveRepositoryTest {

    private val mockedApi = mockkClass(ArchiveApi::class)
    private val classUnderTest = ArchiveRepository(mockedApi)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchData_success() = runTest{

        coEvery {
            mockedApi.getArchive()
        }.returns(Response.success(
            ArchiveSamplerResponse(files = listOf(ArchiveSamplerResponse.File(name = "ABC")))
        ))

        classUnderTest.fetchData().collectLatest {
            assertTrue(it.first().name == "ABC")
        }

        coVerify { mockedApi.getArchive() }



    }

    @Test
    fun fetchData_success_emptyData() = runTest{

        coEvery {
            mockedApi.getArchive()
        }.returns(Response.success(
            ArchiveSamplerResponse())
        )


        classUnderTest.fetchData()
            .catch { assertTrue(it.message == "No Data available") }
            .collect()

        coVerify { mockedApi.getArchive() }





    }

    @Test
    fun fetchData_failure() = runTest{

        coEvery {
            mockedApi.getArchive()
        }.returns(Response.error(
            400, ResponseBody.create(MediaType.parse("plain/text"), "Error info"))
        )


        classUnderTest.fetchData()
            .catch {
                assertTrue(true)
            }
            .collect()

        coVerify { mockedApi.getArchive() }





    }
}