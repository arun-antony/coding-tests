package com.example.sampleapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val albumDataSource: AlbumDataSource) : AlbumRepository{

    override suspend fun getAlbumData(): Flow<Result<List<AlbumData>>>  = flow {

        val result = try {
            val response = albumDataSource.getAlbumList()

            when {
                response.isSuccessful -> {

                    response.body()?.let {
                        Result.success(it)
                    } ?: run {
                        throw Exception("Empty response")
                    }
                }

                else -> {
                    throw Exception(response.errorBody().toString())
                }
            }

        } catch (e: Exception){
            Result.failure(e)
        }

        emit(result)
    }
}