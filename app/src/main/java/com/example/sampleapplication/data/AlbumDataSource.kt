package com.example.sampleapplication.data

import retrofit2.Response
import javax.inject.Inject

class AlbumDataSource @Inject constructor(private val albumApi: AlbumApi) {

    suspend fun getAlbumList(): Response<List<AlbumData>>{
        return albumApi.getAlbums()
    }
}