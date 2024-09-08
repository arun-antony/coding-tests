package com.example.sampleapplication.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface AlbumApi {

    @GET("/albums")
    suspend fun getAlbums(): Response<List<AlbumData>>
}

@Module
@InstallIn(ViewModelComponent::class)
object AlbumApiModule{

    @Provides
    fun provideAlbumApi(retrofit: Retrofit): AlbumApi{
        return retrofit.create(AlbumApi::class.java)
    }
}