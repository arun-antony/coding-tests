package com.example.sampleapplication.feature_worldbank.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface ArchiveApi {

    @GET("metadata/TheAdventuresOfTomSawyer_201303")
    suspend fun getArchive(): Response<ArchiveSamplerResponse>
}

@Module
@InstallIn(ViewModelComponent::class)
object ArchiveApiModule{

    @Provides
    fun provide(retrofit: Retrofit): ArchiveApi{
        return retrofit.create(ArchiveApi::class.java)
    }
}

