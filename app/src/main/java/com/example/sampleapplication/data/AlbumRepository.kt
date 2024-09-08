package com.example.sampleapplication.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbumData(): Flow<Result<List<AlbumData>>>
}

@Module
@InstallIn(ViewModelComponent::class)
interface AlbumRepositoryProvider{

    @Binds
    fun bindAlbumRepository(albumRepositoryImpl: AlbumRepositoryImpl): AlbumRepository
}